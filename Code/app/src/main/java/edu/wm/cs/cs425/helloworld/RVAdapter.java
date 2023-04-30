package edu.wm.cs.cs425.helloworld;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;






public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReviewModel> reviewModelArrayList;
    public RVAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList){
        this.context = context;
        this.reviewModelArrayList = reviewModelArrayList;
    }
    @NonNull
    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_row,parent, false);

        return new RVAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.MyViewHolder holder, int position) {
        holder.foodname.setText(reviewModelArrayList.get(position).getFoodName());
        holder.locationname.setText(reviewModelArrayList.get(position).getFoodLocation());
        holder.calories.setText(reviewModelArrayList.get(position).getCalories());
    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView foodname, locationname, calories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.foodpic);
            foodname = itemView.findViewById(R.id.item_name);
            locationname = itemView.findViewById(R.id.item_location);
            calories = itemView.findViewById(R.id.calories);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String uid = user.getUid();
            itemView.findViewById(R.id.favorite_heart).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("demo", "clickly");
                    Map<String, Object> data = new HashMap<>();
                    data.put("locationname", locationname.getText().toString());
                    data.put("calories", calories.getText().toString());
                    data.put("foodname", foodname.getText().toString());
                    db.collection("users").document(uid).collection("favorites")
                            .document(foodname.getText().toString()).set(data).addOnSuccessListener(new OnSuccessListener<Void>(){
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("Firestore", "added " + foodname + " to database");
                                }
                            }).addOnFailureListener(new OnFailureListener(){
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Firestore", "Could not add to database");
                                }
                            });

                }
            });
            itemView.findViewById(R.id.rstar1).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(view.getContext(), Leave_Review.class);
                   intent.putExtra("food", foodname.getText());
                   intent.putExtra("location", locationname.getText());
                   intent.putExtra("rating", 1);
                   intent.putExtra("image", R.id.foodpic);
                   view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar2).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 2);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar3).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 3);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar4).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 4);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar5).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 5);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.info).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("demo", "clickly");
                }
            });

        }
    }
}

