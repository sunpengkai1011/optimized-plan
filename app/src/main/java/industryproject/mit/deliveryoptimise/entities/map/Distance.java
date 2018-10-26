package industryproject.mit.deliveryoptimise.entities.map;

import java.io.Serializable;

public class Distance implements Serializable{

    private String text;
    private int value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
