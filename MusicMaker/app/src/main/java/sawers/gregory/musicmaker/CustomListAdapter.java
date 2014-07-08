package sawers.gregory.musicmaker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gregory.Sawers on 7/3/2014.
 */
public class CustomListAdapter extends ArrayAdapter<MenuItem> {

    private Context context;
    public CustomListAdapter(Context context, int resourceId, List<MenuItem> items){

        super(context, resourceId, items);
        this.context = context;

    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MenuItem item = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_item_layout, null);
            holder = new ViewHolder();

            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDesc.setText(item.getDesc());
        holder.txtTitle.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImageId());

        return convertView;
    }

}
