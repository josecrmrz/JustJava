/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            // Show error message that user cannot go over 100 cups of coffee
            Toast.makeText(getApplicationContext(), "You cannot order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();

            return;
        }

        quantity++;

        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            // Show error message that user cannot go under 1 cup of coffee
            Toast.makeText(getApplicationContext(), "You cannot order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();

            return;
        }

        quantity--;

        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox chkWhippedCream = findViewById(R.id.check_whipped_cream);
        boolean addWhippedCream = chkWhippedCream.isChecked();

        CheckBox chkChocolate = findViewById(R.id.check_chocolate);
        boolean addChocolate = chkChocolate.isChecked();

        EditText etName = findViewById(R.id.edit_name);
        Editable name = etName.getText();

        int price = calculatePrice(quantity, addWhippedCream, addChocolate);

        displayMessage(createOrderSummary(price, addWhippedCream, addChocolate, name));
    }


    /**
     * Creates a summary of the Order.
     *
     * @param price is the price total for coffee
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, Editable name) {
        String priceMessage = String.format("Name: %1$s\n", name);
        priceMessage = priceMessage + String.format("Add whipped cream? %1$s\n", addWhippedCream);
        priceMessage = priceMessage + String.format("Add chocolate? %1$s\n", addChocolate);
        priceMessage = priceMessage + String.format("Quantity: %1$s\n", quantity);
        priceMessage = priceMessage + String.format("Total: %1$s\nThank you!", NumberFormat.getCurrencyInstance().format(price));

        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity        is the number of cups of coffee ordered
     * @param addWhippedCream if the user wants whipped cream
     * @param addChocolate    if the user wants chocolate
     */
    private int calculatePrice(int quantity, boolean addWhippedCream, boolean addChocolate) {
        int price = quantity * 5;

        if (addWhippedCream) {
            price += 1;
        }

        if (addChocolate) {
            price += 2;
        }

        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.text_quantity);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given message on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.text_order_summary);
        orderSummaryTextView.setText(message);
    }
}