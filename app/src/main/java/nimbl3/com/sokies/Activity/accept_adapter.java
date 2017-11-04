package nimbl3.com.sokies.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by MY on 11/3/2017.
 */

public class accept_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<accept_list> listItemsList;
    private android.content.Context Context;
    private com.android.volley.toolbox.ImageLoader ImageLoader;
    private int focusedItem = 0;
    String order_no1, data;

    private android.widget.Button Button;

    public accept_adapter(android.content.Context context, List<accept_list> listItemsList) {
        this.listItemsList = listItemsList;
        this.Context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.assigned_form, null);
        ListRowViewHolder holder = new ListRowViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        accept_list advisorname = listItemsList.get(position);
        ListRowViewHolder holder2 = (ListRowViewHolder) holder;
        holder.itemView.setSelected(focusedItem == position);
        holder.getLayoutPosition();


        // Methods.glide_image_loader_fixed_size(advisorname.getImages(), holder2.thumbnail);


        holder2.order_no.setText(advisorname.getOrder_no1());
     /*   holder2.deliver.setText(advisorname.getDeliver_date1());
        holder2.ratio.setText(advisorname.getRatio1());
        holder2.remarks.setText(advisorname.getRemarks1());
        holder2.quantity.setText(advisorname.getQuantity1());
        holder2.order_date.setText(advisorname.getOrder_date1());
        holder2.party.setText(advisorname.getParty1());
        holder2.size.setText(advisorname.getSize1());
        holder2.type.setText(advisorname.getType());
        holder2.project.setText(advisorname.getProject1());*/

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
        protected TextView Comapany_name, date, order_no, order_date, size, type, drawing, ratio, remarks, quantity, deliver, project, party, HairStraightening, HairColouring, HairCut, HairCute;
        String c1, data;
        ImageView style_image, style_images, style_imagess;
        android.widget.Button next, previous, save;
        private RatingBar ratingBar;
        private CardView card;
        RequestQueue requestQueue;
        private CheckBox Checkbox1;
        ProgressDialog progressDialog;
        private StringRequest stringRequest;

        SharedPreferences preferences;

        protected RelativeLayout relativeLayout;


        public ListRowViewHolder(View view) {
            super(view);
            // this.thumbnail = (ImageView) view.findViewById(R.id.iv_news_image);
            //  this.id = (TextView) view.findViewById(R.id.txt_id);


            this.order_no = (TextView) view.findViewById(R.id.orde_no1);
          /*  this.order_date = (TextView) view.findViewById(R.id.order_date1);
            this.size = (TextView) view.findViewById(R.id.size1);
            this.type = (TextView) view.findViewById(R.id.type1);
            this.drawing = (TextView) view.findViewById(R.id.drawing1);
            this.ratio = (TextView) view.findViewById(R.id.ratio1);
            this.quantity = (TextView) view.findViewById(R.id.quantity1);
            this.deliver = (TextView) view.findViewById(R.id.delivery1);
            this.project = (TextView) view.findViewById(R.id.project_manager);
            this.party = (TextView) view.findViewById(R.id.party1);
            this.remarks = (TextView) view.findViewById(R.id.remarks1);*/
            this.card = (CardView) view.findViewById(R.id.card_view1);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent_transfer(getAdapterPosition());
                }
            });

        }


        @Override
        public void onClick(View v) {

        }
    }

    public void intent_transfer(int position) {
        Intent intent = new Intent(Context, accept_assignform.class);
        intent.putExtra("order_no", listItemsList.get(position).getOrder_no1());
        Context.startActivity(intent);
        clearAdapter();
    }


}
