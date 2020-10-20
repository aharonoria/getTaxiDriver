package com.example.android5778_7822_2864;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android5778_7822_2864.entities.Driver;
import com.example.android5778_7822_2864.model.backend.BackendFactory;
import com.example.android5778_7822_2864.model.dataSource.DatabaseFB;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDrivers2 extends Fragment {

    public List<Driver> drivers=new ArrayList<Driver>();
    private RecyclerView recyclerViewOfDrivers1;
    public FragmentDrivers2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_drivers2, container, false);
        recyclerViewOfDrivers1 = view.findViewById(R.id.recyclerViewOfDrivers2);
        recyclerViewOfDrivers1.setHasFixedSize(true);
        recyclerViewOfDrivers1.setLayoutManager(new LinearLayoutManager(getActivity()));

        drivers= BackendFactory.getInstance(getActivity()).getDriverList();
        recyclerViewOfDrivers1.setAdapter(new FragmentDrivers2.DriverRecycleViewAdapter());
        return view;
    }
    public void onDestroy() {
        DatabaseFB.stopNotifyToTravelList();
        super.onDestroy();
    }



    public class DriverRecycleViewAdapter extends RecyclerView.Adapter<FragmentDrivers2.DriverRecycleViewAdapter.DriverViewHolder>

    {
        @NonNull
        @Override
        public FragmentDrivers2.DriverRecycleViewAdapter.DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_row__item, parent, false);
            return  new DriverRecycleViewAdapter.DriverViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
            Driver driver = drivers.get(position);

            holder.nameTextView.setText(driver.getFirstName());

            holder.phoneTextView.setText(driver.getPhone());
        }

        @Override
        public int getItemCount() {
            return drivers.size();        }

        public class DriverViewHolder extends RecyclerView.ViewHolder {
            TextView nameTextView;
            TextView phoneTextView;


            DriverViewHolder(View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.nameTextView);
                phoneTextView = itemView.findViewById(R.id.phoneTextView);

                itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                      /*  menu.setHeaderTitle("Select Action");
                        MenuItem select = menu.add(Menu.NONE, 1, 1, "Show");

                        select.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                int position=getAdapterPosition();
                                //long id=travels.get(position).getId();
                                Driver d = drivers.get(position);
                                FragmentManager fM = getFragmentManager();
                                FragmentTransaction fT = fM.beginTransaction();
                                FragmentTravelOfDriver fragment1 = new FragmentTravelOfDriver(d);
                                fT.replace(R.id.container2, fragment1);
                                fT.commit();
                                return true;
                            }
                        });*/

                    }
                });
            }
        }
    }

}
