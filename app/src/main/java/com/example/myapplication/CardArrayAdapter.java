package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardArrayAdapter extends ArrayAdapter<Explain> {

    private static final String TAG = "CardArrayAdapter";
    private List<Explain> cardList = new ArrayList<Explain>();

    static class CardViewHolder {
        TextView line1;
        TextView line2;
        TextView line3;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Explain object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Explain getItem(int index) {
        return this.cardList.get(index);
    }



    @Override
    public  View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.fragment_first, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.wrong);
            viewHolder.line2 = (TextView) row.findViewById(R.id.right);
            viewHolder.line3 = (TextView) row.findViewById(R.id.explaination);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        Explain card = getItem(position);
        viewHolder.line1.setText(card.getOriginalWord());
        viewHolder.line2.setText(card.getReplacedWord());
        viewHolder.line3.setText(card.getExplaination());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
