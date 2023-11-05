package com.aacademy.moonlight.controller.paypal;
import com.aacademy.moonlight.service.hotel.RoomReservationService;
import com.aacademy.moonlight.service.paypal.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/paypal")
public class PaypalController {

    private final PaypalService payPalService;
    private final RoomReservationService roomReservationService;


    @Secured("USER")
    @PostMapping("/payment")
    public String makePayment (@RequestParam("reservationId") Long reservationId,
                               @RequestParam("entityType") String entityType,
                               @RequestParam("cancelUrl") String cancelUrl,
                               @RequestParam("successUrl") String successUrl) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication.getAuthorities().stream().noneMatch(role -> role.getAuthority().equals("USER"))) {
            System.out.println("Unauthorized: User does not have the required role to make a payment.");
        }

        try {
            Object reservation = null;

            if ("room".equals(entityType)) {
                reservation = roomReservationService.findRoomReservationById(reservationId);
            }

            if (reservation == null) {
                System.out.println("Reservation not found");
            }

            double totalAmount = payPalService.getTotalAmount(reservation);
            System.out.println("Total amount in controller " + totalAmount);

            Payment payment = payPalService.createPayment(totalAmount, "USD", "PAYPAL", cancelUrl, successUrl);
            return payment.getLinks().get(1).getHref();

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}