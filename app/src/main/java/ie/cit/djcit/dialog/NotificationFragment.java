package ie.cit.djcit.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class NotificationFragment extends DialogFragment {
    public static NotificationFragment newInstance(int title, int msg, int pic){

        NotificationFragment displayObjectFragment = new NotificationFragment();

        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("msg", msg);
        args.putInt("pic",pic);
        displayObjectFragment.setArguments(args);
        return displayObjectFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int msg = getArguments().getInt("msg");
        final int title = getArguments().getInt("title");
        int pic = getArguments().getInt("pic");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(pic);
        builder.setTitle(title);
        if(msg != 0) {
            builder.setMessage(msg);
        }

        return builder.create();
    }

    public interface dialogListener {
        void notificationListener(DialogFragment dialog);
    }

    NotificationFragment.dialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NotificationFragment.dialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}

