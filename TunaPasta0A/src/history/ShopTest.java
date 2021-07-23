package history;
public class ShopTest<V> {
	V product; //进来什么属性,就用什么属性操作~
	public ShopTest(V product){
		this.product = product;
	}
	public V buy(double money){
		System.out.println(product.getClass().getName());
		return product; 
	}
	public static void main(String[] args) {
		ShopTest<Food> foodShop = new ShopTest<Food>(new Food());
		System.out.println(foodShop.buy(2));
		
		Pet p = new Pet();
		ShopTest<Pet> petShop = new ShopTest<Pet>(p);
		System.out.println(petShop.buy(20));
	}
}

class Food{
	public String toString(){
		return "食物";
	}
}
class Pet{
	public String toString(){
		return "宠物";
	}
}