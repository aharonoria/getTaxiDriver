package com.example.android5778_7822_2864;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android5778_7822_2864.entities.Travel;


import com.example.android5778_7822_2864.model.backend.Backend;
import com.example.android5778_7822_2864.model.backend.BackendFactory;
import com.example.android5778_7822_2864.model.dataSource.DatabaseFB;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.android5778_7822_2864.R.layout.abc_expanded_menu_layout;
import static com.example.android5778_7822_2864.R.layout.fragment__my__travels;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_My_Travels extends Fragment {
   // public String [] str={"Oria","Oshrit","Talya"};
    public List<Travel> travels=new ArrayList<Travel>();
    private RecyclerView recyclerViewOfTravels;
    private Long idOfDriver=new Long(-1);
   // public Backend fb=BackendFactory.getInstance(getActivity());

    public void setId(Long id)
    {
     idOfDriver=id;
    }
public void setTravels(List<Travel> travelsAction)
{
    travels=travelsAction;
}
    /**
     * onCreateView
     * replace between fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(fragment__my__travels, container, false);

        recyclerViewOfTravels = view.findViewById(R.id.recyclerViewOfTravels);
        recyclerViewOfTravels.setHasFixedSize(true);
        recyclerViewOfTravels.setLayoutManager(new LinearLayoutManager(getActivity()));

        //travels=BackendFactory.getInstance(getActivity()).getAllMyTravels(idOfDriver);
        recyclerViewOfTravels.setAdapter(new TravelRecycleViewAdapter());
        return view;
    }



   public void onDestroy() {
        DatabaseFB.stopNotifyToTravelList();
        super.onDestroy();
    }



    public class TravelRecycleViewAdapter extends RecyclerView.Adapter<Fragment_My_Travels.TravelRecycleViewAdapter.TravelViewHolder>

    {
        @NonNull
        @Override
        public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_row__item, parent, false);

            return new TravelViewHolder(v);
            }

        @Override
        public void onBindViewHolder(@NonNull TravelRecycleViewAdapter.TravelViewHolder holder, int position) {
            Travel travel = travels.get(position);

            holder.nameTextView.setText(travel.getName());

            holder.phoneTextView.setText(travel.getPhone());

            //Load the image using Glide


        }

        @Override
        public int getItemCount() {
            return travels.size();        }

        public class TravelViewHolder extends RecyclerView.ViewHolder {
            TextView nameTextView;
            TextView phoneTextView;


             TravelViewHolder(View itemView) {
                super(itemView);
                 nameTextView = itemView.findViewById(R.id.nameTextView);
                 phoneTextView = itemView.findViewById(R.id.phoneTextView);

                 itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                     @Override
                     public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                         menu.setHeaderTitle("Select Action");
                         MenuItem select = menu.add(Menu.NONE, 1, 1, "Show");

                         select.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                             @Override
                             public boolean onMenuItemClick(MenuItem item) {
                                int position=getAdapterPosition();
                                //long id=travels.get(position).getId();
                                 Travel t = travels.get(position);
                                 FragmentManager fM = getFragmentManager();
                                 FragmentTransaction fT = fM.beginTransaction();
                                 FragmentTravelOfDriver fragment1 = new FragmentTravelOfDriver(t);
                                 fT.replace(R.id.container2, fragment1);
                                 fT.commit();
                                return true;
                             }
                         });
                         MenuItem AddToMyTravels = menu.add(Menu.NONE, 1, 1, "Finish Travel");

                         AddToMyTravels.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                             @Override
                             public boolean onMenuItemClick(MenuItem item) {
                                 int position = getAdapterPosition();
                                 Travel t = travels.get(position);
                                 BackendFactory.getInstance(getActivity()).finishTravel(t);
                                 Toast.makeText(getActivity(), "The ride was finished!", Toast.LENGTH_LONG).show();
                                 travels.remove(getAdapterPosition());
                                 recyclerViewOfTravels.getAdapter().notifyDataSetChanged();
                                 return true;
                             }
                         });
                     }
                 });
                 }
        }
    }

}
