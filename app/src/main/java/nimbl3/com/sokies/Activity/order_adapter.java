package nimbl3.com.sokies.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import nimbl3.com.sokies.R;

/**
 * Created by MY on 10/31/2017.
 */

public class order_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<order_list> listItemsList;
    private android.content.Context Context;
    private com.android.volley.toolbox.ImageLoader ImageLoader;
    private int focusedItem = 0;


    private android.widget.Button Button;

    public order_adapter (android.content.Context context, List<order_list> listItemsList) {
        this.listItemsList = listItemsList;
        this.Context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.orderformedit , null);
        ListRowViewHolder holder = new ListRowViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        order_list advisorname = listItemsList.get(position);
        ListRowViewHolder holder2 = (ListRowViewHolder) holder;
        holder.itemView.setSelected(focusedItem == position);
        holder.getLayoutPosition();


        // Methods.glide_image_loader_fixed_size(advisorname.getImages(), holder2.thumbnail);




        holder2.Comapany_name.setText(advisorname.getProject_manager());
        holder2.order_no.setText(advisorname.getOrder_no());
        holder2.date.setText(advisorname.getDate1());


        //   holder2.HairCute.setText(advisorname.getId());

    }


    public void clearAdapter() {
        listItemsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return (null != listItemsList ? listItemsList.size() : 0);
    }

    public class ListRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView thumbnail;
        protected TextView Comapany_name,date,order_no, HairStraightening, HairColouring, HairCut, HairCute;
        String c1, data;
        ImageView style_image,style_images,style_imagess;
        android.widget.Button next, previous,save;
        private RatingBar ratingBar;
        private CardView card;
        RequestQueue requestQueue;
        private CheckBox Checkbox1;

        private StringRequest stringRequest;

        SharedPreferences preferences;

        protected RelativeLayout relativeLayout;


        public ListRowViewHolder(View view) {
            super(view);
            // this.thumbnail = (ImageView) view.findViewById(R.id.iv_news_image);
            //  this.id = (TextView) view.findViewById(R.id.txt_id);


            this.Comapany_name= (TextView) view.findViewById(R.id.textView9);
            this.order_no= (TextView) view.findViewById(R.id.textView10);

            this.date= (TextView) view.findViewById(R.id.textView11);
            next=(Button) view.findViewById(R.id.btn_update_order);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent_transfer(getAdapterPosition());
                    //     Intent ieventreport = new Intent(Context,style_adapter.class);
                    //  Context.startActivity(ieventreport);
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
    public void intent_transfer(int position) {
        Intent intent = new Intent(Context, Update_order.class);
        intent.putExtra("order_no", listItemsList.get(position).getOrder_no());
        Context.startActivity(intent);
    }



}
