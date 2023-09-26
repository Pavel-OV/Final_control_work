package nurseryanimal;

public class Counter {
    private int quantity;

    public Counter() {
        this.quantity = 0;
    }

    public void setCount(int count) {
        quantity = count;

    }

    public void add() {
        quantity++;
    }

    public void remove() {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
        }
    }
}
