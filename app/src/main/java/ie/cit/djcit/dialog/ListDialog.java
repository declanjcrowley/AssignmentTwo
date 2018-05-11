package ie.cit.djcit.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ie.cit.djcit.assignmenttwo.R;

public class ListDialog extends DialogFragment {

    public String TAG = "test";

    public static ListDialog newInstance() {

        ListDialog listDialog = new ListDialog();

        Bundle args = new Bundle();
        listDialog.setArguments(args);
        return listDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG,"WRF.onCreateDialog()");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.listtitle);

        LayoutInflater li = LayoutInflater.from(getActivity());
        View view = li.inflate(R.layout.lis_dialog_layout, null);
        String [] arrayWork = getResources().getStringArray(R.array.list);
        ListArrayAdater adapter = new ListArrayAdater(getActivity(), arrayWork);

        ListView listView=(ListView) view.findViewById(R.id.listid);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(TAG,"WRF.onCreateDialog() position:id " + position + "," + id);
                String [] arrayWork = getResources().getStringArray(R.array.list);
                mListener.listDialogSelection(ListDialog.this, arrayWork[position]);
            }
        });
        builder.setView(view);
        return builder.create();
    }

    public interface ListDialogListener {
        void listDialogSelection(DialogFragment dialog, String work);
    }
    ListDialog.ListDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ListDialog.ListDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}

