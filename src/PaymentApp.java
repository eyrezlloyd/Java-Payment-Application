// PaymentMode interface
interface PaymentMode {
    double calculateDiscount(double totalAmount);
}

// Concrete implementations of PaymentMode
class GCash implements PaymentMode {
    @Override
    public double calculateDiscount(double totalAmount) {
        return 0.0; // No discount for GCash
    }
}

class Maya implements PaymentMode {
    @Override
    public double calculateDiscount(double totalAmount) {
        return totalAmount * 0.05; // 5% discount for Maya
    }
}

class ShopeePay implements PaymentMode {
    @Override
    public double calculateDiscount(double totalAmount) {
        return totalAmount * 0.10; // 10% discount for ShopeePay
    }
}

// Order class
class Order {
    private String item;
    private int quantity;
    private double unitPrice;
    private PaymentMode paymentMode;
    private double totalAmount;

    public Order(String item, int quantity, double unitPrice, PaymentMode paymentMode) {
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.paymentMode = paymentMode;
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        double totalAmount = quantity * unitPrice;
        double discount = paymentMode.calculateDiscount(totalAmount);
        this.totalAmount = totalAmount - discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayPaymentDetails() {
        System.out.println("Order item is " + item);
        System.out.println("Unit price is " + unitPrice);
        System.out.println("Quantity is " + quantity);
        System.out.println();
        System.out.println("Payment order details:");
        System.out.println("Discount rate is " + paymentMode.calculateDiscount(unitPrice * quantity));
        System.out.println("Payment amount is " + totalAmount);
        System.out.println();
    }
}

// PaymentApp class (entry point for the application)
public class PaymentApp {
    public static void main(String[] args) {
        Order order;

        // Creating an Order object with GCash payment
        order = new Order("keyboard", 10, 300.0, new GCash());
        System.out.println("Payment order details if GCash:");
        order.displayPaymentDetails();

        // Creating an Order object with Maya payment
        order = new Order("keyboard", 10, 300.0, new Maya());
        System.out.println("Payment order details if Maya:");
        order.displayPaymentDetails();

        // Creating an Order object with ShopeePay payment
        order = new Order("keyboard", 10, 300.0, new ShopeePay());
        System.out.println("Payment order details if ShopeePay:");
        order.displayPaymentDetails();
    }
}