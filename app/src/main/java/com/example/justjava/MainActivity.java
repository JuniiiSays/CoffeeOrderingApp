package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    final int PRICE_OF_COFFEE = 5;
    final int PRICE_OF_WHIPPED_CREAM = 1;
    final int PRICE_OF_CHOCOLATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is Called when the Increment button is called.
     */
    public void increment(View view){
        if (quantity >= 100){
            Toast toast = Toast.makeText(this, "You cannot have more then 100 coffees", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }else
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is Called when the Decrement button is Clicked.
     */
    public void decrement(View view){
        if(quantity <= 1){
            Toast toast = Toast.makeText(this, "You cannot have Less then 1 coffee", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }else
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
   public void submitOrder (View view){
       boolean hasWhippedCream;
       boolean hasChocolate;

       CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_check_box);
       CheckBox chocolateCheckBox = findViewById(R.id.chocolate_check_box);
       EditText nameEditText = findViewById(R.id.name_edit_text);
       String nameInput = nameEditText.getText().toString();

       hasWhippedCream = whippedCreamCheckBox.isChecked();
       hasChocolate = chocolateCheckBox.isChecked();

       int price = calculatePrice(hasWhippedCream, hasChocolate, quantity, PRICE_OF_COFFEE);
       String orderSummary = createOrderSummary(nameInput, price, hasWhippedCream, hasChocolate);

       Intent intent = new Intent(Intent.ACTION_SEND);
       intent.setType("plain/text");
       intent.putExtra(Intent.EXTRA_SUBJECT,   getString(R.string.app_name) + "order for" + nameInput);
       intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
       if (intent.resolveActivity(getPackageManager()) != null){
           startActivity(Intent.createChooser(intent, "Chose an Email client"));
       }
       /*displayMessage(orderSummary);*/
   }

    /**
     * This method display the quantity on the screen.
     * @param numberOfCoffees
     */
    public void displayQuantity(int numberOfCoffees){
        TextView quantityTextView = (TextView) findViewById(R.id.qunaity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     *This method display the given text on the screen.
     * @param message
     */
    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method will calculate the price of the Order
     * @param quantityOfCoffes How much coffees ordered.
     * @param perCupPrice Price of a single coffee cup.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate ,int quantityOfCoffes, int perCupPrice){
        if (addWhippedCream == true){
            perCupPrice = perCupPrice + PRICE_OF_WHIPPED_CREAM;
        }
        if (addChocolate == true){
            perCupPrice = perCupPrice + PRICE_OF_CHOCOLATE;
        }
        return quantityOfCoffes * perCupPrice;
    }

    /**
     * This method will show the Summary of the order
     */
    private String createOrderSummary(String nameInput ,int price, boolean hasWhippedCream, boolean hasChocolate){
        String orderSummary;
        orderSummary = "Name: " + nameInput;
        orderSummary = orderSummary + "\nAdd Whipped Cream? " + hasWhippedCream;
        orderSummary = orderSummary + "\nAdd Chocolate? " + hasChocolate;
        orderSummary = orderSummary + "\nQuantity: " + quantity;
        orderSummary = orderSummary + "\nTotal: $" + price;
        orderSummary = orderSummary + "\nThank you!";
        return orderSummary;
    }
}