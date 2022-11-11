package hanifm.anyx.hanifm.anyx.test.config;

public enum PaymentMethodEnum {

    CASH(0.9, 1.0),
    CASH_ON_DELIVERY(1.0, 1.02),
    VISA(0.95, 1.0),
    MASTERCARD(0.95, 1),
    AMEX(0.98, 1.01),
    JCB(0.95, 1.0);

    PaymentMethodEnum(double adjustmentDown, double adjustmentUp) {
        this.adjustmentDown = adjustmentDown;
        this.adjustmentUp = adjustmentUp;
    }

    public final double adjustmentDown;
    public final double adjustmentUp;
}
