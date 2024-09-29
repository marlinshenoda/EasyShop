package com.payex.shopease;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private CardView addToCartCard;
    private Button viewCartButton;
    private ListView listView;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Initialize the UI components
        addToCartCard = findViewById(R.id.add_to_cart_card);
        viewCartButton = findViewById(R.id.view_cart_button);
        listView = findViewById(R.id.list_view);

        // Initialize the item list and adapter
        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        // Handle click event for the "Add to Cart" CardView
        addToCartCard.setOnClickListener(view -> showAddItemDialog());

        // Handle click event for the "View Cart" button
        viewCartButton.setOnClickListener(view -> {
            if (itemList.isEmpty()) {
                Toast.makeText(MainActivity.this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                // Navigate to CartActivity
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putStringArrayListExtra("cartItems", itemList); // Pass the cart items to CartActivity
                startActivity(intent);
            }
        });
    }

    // Show a dialog to add an item to the cart
    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Item");

        final EditText input = new EditText(MainActivity.this);
        input.setHint("Enter item name");
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String itemName = input.getText().toString().trim();
            if (!itemName.isEmpty()) {
                itemList.add(itemName); // Add item to the list
                adapter.notifyDataSetChanged(); // Notify adapter of the change
                Toast.makeText(MainActivity.this, itemName + " added to cart!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Item name cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();

    }
}

