package current;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 2019-11-27 13:31
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */
public class ItemTest {
    static List<Item> itemList = new ArrayList();

    static class B {
        int t;

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
            Item item = new Item(1);
            item.setOnChangeListener(new Item.OnChangeListener() {
                @Override
                public void onChange(int change) {
                    B.this.t = change;
                }
            });
            itemList.add(item);
        }
    }

    static class Item {
        public Item(int value) {
            this.value = value;
        }

        int value;
        private OnChangeListener onChangeListener;

        public interface OnChangeListener {
            void onChange(int change);
        }

        public void setValue(int value) {
            this.value = value;
            this.onChangeListener.onChange(value);
        }

        public void setOnChangeListener(OnChangeListener onChangeListener) {
            this.onChangeListener = onChangeListener;
        }


    }

    public static void main(String[] args) {
        B b = new B();
        b.setT(1);
        System.out.println("b.getT()==>" + b.getT());
        itemList.get(0).setValue(2);
        System.out.println("b.getT()==>" + b.getT());

    }

}
