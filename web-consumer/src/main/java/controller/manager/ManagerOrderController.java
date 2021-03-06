package controller.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import dto.Order;
import entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.OrderService;
import service.UserService;
import util.OrderUtil;

import java.util.*;

@Controller
@RequestMapping("manager/order")
@Api(tags = "管理员对用户订单获取，查看")
public class ManagerOrderController {
    @Reference
    OrderService orderService;
    @Reference
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("获取所有的订单")
    public ModelAndView getOrders() {
        ModelAndView mav = new ModelAndView("manager/orders");
        List<Order> orders = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        List<? extends User> users = userService.getAllUsers(0);
        for (User user : users) {
            List<Order> orders1 = orderService.getOrders(user.getName(), 0);
            for (Order order : orders1) {
                orders.add(order);
                values.add(OrderUtil.calculateOrderValue(order));
            }
        }

        mav.addObject("orders", orders);
        mav.addObject("values", values);
        return mav;
    }

    @RequestMapping(value = "/{userKind}/{userName}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取某用户所有的订单")
    public Map<Order, Double> getUserOrders(@PathVariable("userKind") Integer userKind,
                                     @PathVariable("userName") String userName) {
        List<Order> orders = orderService.getOrders(userName, userKind);
        Map<Order, Double> result = new HashMap<>();
        for (Order order : orders) {
            result.put(order, OrderUtil.calculateOrderValue(order));
        }
        return result;
    }

    @RequestMapping(value = "/create_time", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据用户创建订单时间获取订单信息")
    public Map<Order, Double> getOrdersByCreateTime(@RequestParam("fromTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fromTime,
                                           @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
        List<Order> orders = new ArrayList<>();
        List<? extends User> users = userService.getAllUsers(0);

        for (User user : users) {
            List<Order> orders1 = orderService.getOrders(user.getName(), 0);
            orders.addAll(orders1);
        }

        Map<Order, Double> result = new HashMap<>();
        for (Order order : orders) {
            Date createTime = order.getOrderItem().getCreateTime();
            if (createTime.after(fromTime) && createTime.before(endTime)) {
                result.put(order, OrderUtil.calculateOrderValue(order));
            }
        }
        return result;
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据订单价值获取订单信息")
    public Map<Order, Double> getOrdersByValue(@RequestParam("from") Double from,
                                             @RequestParam("end") Double end) {
        List<Order> orders = new ArrayList<>();
        List<? extends User> users = userService.getAllUsers(0);

        for (User user : users) {
            List<Order> orders1 = orderService.getOrders(user.getName(), 0);
            orders.addAll(orders1);
        }

        Map<Order, Double> result = new HashMap<>();
        for (Order order : orders) {
            Double value = OrderUtil.calculateOrderValue(order);
            if (value >= from && value <= end) {
                result.put(order, value);
            }
        }
        return result;
    }

    @RequestMapping(value = "/{orderItemId}", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取某订单详情")
    public ModelAndView getOrder(@PathVariable("orderItemId") Integer orderItemId) {
        ModelAndView mav = new ModelAndView("manager/orderDetail");
        Order order = orderService.getOrder(orderItemId);
        mav.addObject("order", order);
        mav.addObject("value", OrderUtil.calculateOrderValue(order));
        return mav;
    }

}
