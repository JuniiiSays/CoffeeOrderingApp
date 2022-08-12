package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    final int PRICE_OF_COFFEE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is Called when the Increment button is called.
     */
    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is Called when the Decrement button is Clicked.
     */
    public void decrement(View view){

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
   public void submitOrder (View view){
       int price = calculatePrice(quantity, PRICE_OF_COFFEE);
       String orderSummary = createOrderSummary(price);
       /*String priceMessage = "Total: $" + price;
       priceMessage = priceMessage + "\nThank you!";*/
       displayMessage(orderSummary);
   }

    /**
     * This method display the quantity on the screen.
     * @param number
     */
    public void displayQuantity(int number){
        TextView textView = (TextView) findViewById(R.id.qunaity_text_view);
        textView.setText("" + number);
    }

    /**
     * This method display the given price on the screen.
     * @param number
     */
    private void displayPrice(int number){
        TextView displayPriceText = (TextView) findViewById(R.id.price_text_view);
        displayPriceText.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     *This method display the given text on the screen.
     * @param message
     */
    private void displayMessage(String message){
        TextView textView = (TextView) findViewById(R.id.price_text_view);
        textView.setText(message);
    }

    /**
     * This method will calculate the price of the Order
     * @param quantityOfCoffes How much coffees ordered.
     * @param perCupPrice Price of a single coffee cup.
     */
    private int calculatePrice(int quantityOfCoffes, int perCupPrice){
        return quantityOfCoffes * perCupPrice;
    }

    /**
     * This method will show the Summary of the order
     */
    private String createOrderSummary(int price){
        String orderSummary;
        orderSummary = "Name: Junaid Iqbal";
        orderSummary = orderSummary + "\nQuantity: " + quantity;
        orderSummary = orderSummary + "\nTotal: $" + price;
        orderSummary = orderSummary + "\nThank you!";
        return orderSummary;
    }
}