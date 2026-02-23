public enum DiscountPhase {
    ITEM_LEVEL(100),
    CART_PERCENTAGE(200),
    CART_FLAT(300),
    PAYMENT_OFFER(400),
    SHIPPING_WAIVER(500);

    private final int weight;

    DiscountPhase(int weight){
        this.weight=weight;
    }

    public int getWeight(){
        return weight;
    }


}
