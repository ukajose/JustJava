package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 98;
    boolean num;
    boolean choco;
    String result;
    int priceChoco = 0;
    int priceWhip = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayEditText();
        displayState();
        displayChoco();
        int price = calculatePrice(priceChoco, priceWhip);
        String priceMsg = createOrderSummary(price);
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JUST JAVA" + result);
        intent.putExtra(Intent.EXTRA_TEXT, priceMsg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMsg);


    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayEditText() {
        EditText edit = (EditText) findViewById(R.id.name);
        TextView EditView = (TextView) findViewById(R.id.name);
        result = edit.getText().toString();
        EditView.setText(result);
    }

    private void displayState() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        num = checkBox.isChecked();
        if (num) {
            priceWhip = 1;
        }
    }

    private void displayChoco() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.choco_checkbox);
        choco = checkBox.isChecked();
        if (choco) {
            priceChoco = 2;
        }
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You can not have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You can not have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @return quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int priceChoco, int priceWhip) {
        int price = quantity * (5 + priceChoco + priceWhip);
        return price;
    }

    private String createOrderSummary(int price) {

        String priceMsg = "Name: " + result + "\nAdd whipped cream ?  " + num + "\nAdd Chocolate ? :  " + choco + "\nQuantity:  " + quantity + "\nTotal: $" + price + "\nThank You !! ";
        return priceMsg;
    }

}