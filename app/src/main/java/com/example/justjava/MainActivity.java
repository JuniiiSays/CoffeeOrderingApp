package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

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
        display(quantity);
    }

    /**
     * This method is Called when the Decrement button is Clicked.
     */
    public void decrement(View view){

        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
   public void submitOrder (View view){
       String priceMessage = "Total: $" + (quantity * 5);
       priceMessage = priceMessage + "\nThank you!";
       displayMessage(priceMessage);
   }

    /**
     * This method display the quantity on the screen.
     * @param number
     */
    public void display(int number){
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
}