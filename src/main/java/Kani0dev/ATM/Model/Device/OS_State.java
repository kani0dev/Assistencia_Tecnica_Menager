package Kani0dev.ATM.Model.Device;

public enum OS_State {
    AWAITING ("Em espera"),
    MAINTENANCE ("Em manutençao"),
    READY( "Pronto"),
    DELIVERED ("Entregue ao cliente");

    private String status;
     OS_State(String s) {
        this.status = s;
    }
}
