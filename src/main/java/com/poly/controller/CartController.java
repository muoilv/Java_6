package com.poly.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.BillDAO;
import com.poly.dao.BillDetailDAO;
import com.poly.dao.CartDAO;
import com.poly.dao.CartDetailDAO;
import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.Cart;
import com.poly.entity.CartDetail;
import com.poly.entity.Product;
import com.poly.entity.User;
import com.poly.service.BillService;
import com.poly.service.CartDetailService;
import com.poly.service.CartService;
import com.poly.service.CookieService;
import com.poly.service.ParamService;
import com.poly.service.ProductService;
import com.poly.service.SessionService;
import com.poly.service.StatusService;
import com.poly.service.UserService;

@Controller
public class CartController {

	@Autowired
	CartService cartSv;
	@Autowired
	ProductService proSv;
	@Autowired
	CartDetailService cartDetailSv;
	@Autowired
	UserService userSv;
	@Autowired
	CartDetailDAO cartDetailDAO;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	BillDAO billDAO;
	@Autowired
	BillDetailDAO billDetailDAO;
	@Autowired
	StatusService sttService;
	@Autowired
	BillService billService;
	@Autowired
	SessionService session;
	@Autowired
	CookieService cookies;
	@Autowired
	ParamService param;
	@Autowired
	HttpServletRequest request;
	int quantity = 1;
	int id;

	@RequestMapping("/cart/list")
	public String list(Model model) {
		User user = userSv.findByEmail(request.getRemoteUser());
		if (user == null) {
			session.set("totalmoney", 0);

		} else {

			// List<CartDetail> list =
			// CartDetailDao.findcartdetail(cookies.getValue("user"));
			Cart cart = cartSv.findByUser(user);
			List<CartDetail> lstCartDetail;
			double totalMoney = 0;

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateNow = formatter.format(date);

			model.addAttribute("dateNow", dateNow);
			if (cart == null) {
				totalMoney = 0;
			} else {
				lstCartDetail = cart.getCartDetails();
				for (CartDetail cartDetail : lstCartDetail) {
					totalMoney += cartDetail.getIntoMoney();
				}
				session.set("totalmoney", totalMoney);
				model.addAttribute("lstCartDetail", lstCartDetail);
				model.addAttribute("user", user);
				if (totalMoney == 0) {
					model.addAttribute("showpay", "");
				}
			}
		}

		return "order/list";
	}

	@RequestMapping("/cart/add")
	public String addCart(Model model) {
		if (param.getString("idproduct", "") != null) {
			id = ((param.getInt("idproduct", 0)));
			Product product = proSv.findById(id);
			// product tồn tại
			if (product != null) {

				User us = userSv.findByEmail(request.getRemoteUser());
				// có user
				if (us != null) {
					User user = userSv.findByEmail(request.getRemoteUser());
					Cart cart = cartSv.findByUser(user);
					if (cart == null) {
						cart = new Cart(null, new Date(), sttService.findByIdStatus("ST04"), user, null);
						cart = cartDAO.save(cart);
					}

					List<CartDetail> listProInCart = cartDetailSv.findAllByCart(cart);

					boolean check = false;
					for (CartDetail cartDetail : listProInCart) {
						if (cartDetail.getProduct().getIdProduct() == product.getIdProduct()) {
							cartDetail.setQuantity(cartDetail.getQuantity() + 1);
							cartDetail.setIntoMoney(cartDetail.getPriceProduct() * cartDetail.getQuantity());
							cartDetailDAO.save(cartDetail);
							check = true;// nếu đã có trong giỏ
						}
					}
					// chưa có trong giỏ
					if (check == false) {
						CartDetail cartDetail = new CartDetail();
						cartDetail.setQuantity(1);
						cartDetail.setNameProduct(product.getNameProduct());
						cartDetail.setProduct(product);
						cartDetail.setPriceProduct(product.getPriceProduct());
						cartDetail.setIntoMoney(product.getPriceProduct());
						cartDetail.setId(0);
						cartDetail.setCart(cart);
						cartDetailDAO.save(cartDetail);
					}
					return "forward:/product/index";

				} else {
					return "/security/login";
				}
			}
			return "forward:/cart/list";
		} else {
			return "forward:/cart/list";
		}

	}

	@RequestMapping("/cart/delete")
	public String delete(Model model) {
		if (param.getString("idproduct", "") != null) {
			id = param.getInt("idproduct", 0);
			Product product = proSv.findById(id);
			if (product != null) {
				User user = userSv.findByEmail(request.getRemoteUser());
				Cart cart = cartSv.findByUser(user);
				List<CartDetail> listProInCart = cartDetailSv.findAllByCart(cart);

				for (CartDetail cartDetail : listProInCart) {
					if (cartDetail.getProduct().getIdProduct() == product.getIdProduct()) {
						cartDetailDAO.delete(cartDetail);
					}
				}
			}
			return "forward:/cart/list";
		} else {
			return "forward:/cart/list";
		}
	}

	@RequestMapping("/cart/update")
	public String update(Model model) {
		if (param.getString("idproduct", "") != null) {
			id = param.getInt("idproduct", 0);
			Product product = proSv.findById(id);
			if (product != null) {
				if (param.getString("quantity-" + id, "") != null) {
					if (param.getInt("quantity-" + id, 1) == 0) {
						User user = userSv.findByEmail(request.getRemoteUser());
						Cart cart = cartSv.findByUser(user);
						List<CartDetail> listProInCart = cartDetailSv.findAllByCart(cart);

						for (CartDetail cartDetail : listProInCart) {
							if (cartDetail.getProduct().getIdProduct() == product.getIdProduct()) {
								cartDetailDAO.delete(cartDetail);
							}
						}
					} else if (param.getInt("quantity-" + id, 1) >= 0) {
						quantity = (param.getInt("quantity-" + id, 1));
						User user = userSv.findByEmail(request.getRemoteUser());
						Cart cart = cartSv.findByUser(user);
						List<CartDetail> listProInCart = cartDetailSv.findAllByCart(cart);
						for (CartDetail cartDetail : listProInCart) {
							if (cartDetail.getProduct().getIdProduct() == product.getIdProduct()) {
								cartDetail.setQuantity(quantity);
								cartDetail.setIntoMoney(cartDetail.getPriceProduct() * quantity);
								cartDetailDAO.save(cartDetail);
							}
						}
					}
				}
			}
		}

		return "forward:/cart/list";
	}

	@RequestMapping("/cart/pay")
	public String addBill(Model model) {
		User user = userSv.findByEmail(request.getRemoteUser());
		if (user != null) {
			Cart cart = cartSv.findByUser(user);
			List<CartDetail> lstCartDT = cartDetailSv.findAllByCart(cart);

			Bill bill = new Bill(null, user.getAddressUser(), new Date(), session.get("totalmoney"),
					sttService.findByIdStatus("ST04"), user, null);

			Bill bi = billService.saveBill(bill);
			for (CartDetail cartDetail : lstCartDT) {
				BillDetail detail = new BillDetail(null, cartDetail.getIntoMoney(), cartDetail.getNameProduct(),
						cartDetail.getPriceProduct(), cartDetail.getQuantity(), true, bi, cartDetail.getProduct());
				billDetailDAO.save(detail);
				cartDetailDAO.delete(cartDetail);
			}
			session.set("displayAllBill", true);
			return "redirect:/cart/checkout";
		} else {
			model.addAttribute("errorCheckout", "Purchaser không thành công");
			return "forward:/cart/list";

		}
	}

	@RequestMapping("/cart/checkout")
	public String cartCheckout(Model model, @RequestParam("idBill") Optional<Integer> idBill) {
		User user = userSv.findByEmail(request.getRemoteUser());
		if (user != null) {
			List<Bill> lstBill = new ArrayList<Bill>();
			lstBill = billService.findByUser(user);
			if (lstBill.isEmpty()) {
				return "order/checkout";
			}
			
			Bill bill = billService.findById(idBill.orElse(lstBill.get(0).getIdBill()));
			model.addAttribute("lstBill", lstBill);
			model.addAttribute("billShow", bill);

//			session.set("checkBill", true);
			return "order/checkout";

		} else {
			return "forward:/cart/list";

		}
	}

}
