package ie.cit.djcit.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ie.cit.djcit.assignmenttwo.R;

/**
 * Created by puter on 09/05/18.
 */

public class ListArrayAdater extends ArrayAdapter<String> {
    private final Context context;
    private String [] items;

    public ListArrayAdater(Context context, String [] stringArray) {
        super(context, R.layout.text_cutom, stringArray);
        this.context = context;
        items = stringArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.text_cutom, parent, false);
        TextView textView1 = (TextView) view.findViewById(R.id.customtextview);
        textView1.setText(items[position]);
        return view;
    }
}
