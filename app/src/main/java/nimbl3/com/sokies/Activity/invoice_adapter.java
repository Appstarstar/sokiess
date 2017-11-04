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
 * Created by MY on 11/1/2017.
 */
public class invoice_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<invoice_list> listItemsList;
    private android.content.Context Context;
    private com.android.volley.toolbox.ImageLoader ImageLoader;
    private int focusedItem = 0;


    private android.widget.Button Button;

    public invoice_adapter (android.content.Context context, List<invoice_list> listItemsList) {
        this.listItemsList = listItemsList;
        this.Context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fetch_invoice , null);
        ListRowViewHolder holder = new ListRowViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        invoice_list advisorname = listItemsList.get(position);
        ListRowViewHolder holder2 = (ListRowViewHolder) holder;
        holder.itemView.setSelected(focusedItem == position);
        holder.getLayoutPosition();


        //Methods.glide_image_loader_fixed_size(advisorname.getImages(), holder2.thumbnail);



        holder2.invoice.setText(advisorname.getInvoice());
        holder2.Comapany_name.setText(advisorname.getName());
      /* holder2.address.setText(advisorname.getAddress());
        holder2.fax.setText(advisorname.getFax_no());
        holder2.kind.setText(advisorname.getKind_attention());
        holder2.renmark.setText(advisorname.getRemark());
        holder2.packing_rate.setText(advisorname.getPacking_rate());
        holder2.packing.setText(advisorname.getPacking_percentage());
        holder2.prepared_designation.setText(advisorname.getPrepared_designation());
        holder2.prepared_mobile.setText(advisorname.getPrepared_mobile());
        holder2.prepared_name.setText(advisorname.getPrepared_name());
        holder2.mobile.setText(advisorname.getMobile());
        holder2.email.setText(advisorname.getEmail());
        holder2.total.setText(advisorname.getTotal_amount());
        holder2.other.setText(advisorname.getOther_charges());
        holder2.gst_invoice.setText(advisorname.getGst_percentage());
        holder2.gst_rate.setText(advisorname.getGst_rate());

        holder2.net.setText(advisorname.getNet_amount());


        holder2.date1.setText(advisorname.getDate());


        //   holder2.HairCute.setText(advisorname.getId());*/

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
        protected TextView Comapany_name,date1,net,invoice,other,address,fax,kind,mobile,email,invoice_no,date,prepared_name,prepared_designation,packing,packing_rate,renmark,gst_invoice,gst_rate,prepared_mobile,total,order_no, HairStraightening, HairColouring, HairCut, HairCute;
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
            this.Comapany_name= (TextView) view.findViewById(R.id.name_invoice);
            this.invoice=(TextView) view.findViewById(R.id.invoice_no_invoice);
             this.card=(CardView) view.findViewById(R.id.card_view_id_qick_order) ;
             card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent_transfer(getAdapterPosition());

                }
            });


         /*  this.address= (TextView) view.findViewById(R.id.address_invoice);
            this.fax= (TextView) view.findViewById(R.id.fax_invoice);
            this.kind= (TextView) view.findViewById(R.id.kind_invoice);
            this.mobile= (TextView) view.findViewById(R.id.mobile_invoice);
            this.email= (TextView) view.findViewById(R.id.email_invoice);
            this.invoice_no= (TextView) view.findViewById(R.id.invoice_no_invoice);
            this.date1= (TextView) view.findViewById(R.id.date_invoice);
            this.prepared_name= (TextView) view.findViewById(R.id.prepared_invoice);
            this.prepared_mobile= (TextView) view.findViewById(R.id.prepared_mobile_invoice);
            this.prepared_designation= (TextView) view.findViewById(R.id.prepared_designation_invoice);
            this.renmark= (TextView) view.findViewById(R.id.remark_invoice);
            this.total= (TextView) view.findViewById(R.id.total_invoice);
            this.packing= (TextView) view.findViewById(R.id.packing_invoice);
            this.packing_rate= (TextView) view.findViewById(R.id.packing_rate_invoice);
            this.gst_invoice= (TextView) view.findViewById(R.id.gst_invoice);
            this.gst_rate= (TextView) view.findViewById(R.id.gst_rate_invoice);
            this.other= (TextView) view.findViewById(R.id.other_invoice);
            this.net= (TextView) view.findViewById(R.id.net_amount_invoice);
*/

        }

        @Override
        public void onClick(View v) {

        }
    }

    public void intent_transfer(int position) {
        Intent intent = new Intent(Context, Viewinvoice.class);
        intent.putExtra("invoice_no", listItemsList.get(position).getInvoice());
        Context.startActivity(intent);
        clearAdapter();
    }

}

