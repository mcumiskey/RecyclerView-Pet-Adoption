package io.mcumiskey.petrescueapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);


        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected pet ID
            int selectedPetId = (int) itemView.getTag();
            Bundle args = new Bundle();
            args.putInt(ProfileFragment.ARG_PET_ID, selectedPetId);

            // Replace list with the profile for clicked pet
            Navigation.findNavController(itemView).navigate(R.id.show_pet_profile, args);
        };

        // Send pets to RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.pet_RecyclerView);
        List<SimplePet> pets = PetRepository.getInstance(requireContext()).getPets();
        recyclerView.setAdapter(new PetAdapter(pets, onClickListener));

        // Inflate the layout for this fragment
        return rootView;
    }

    /**
     * Extention of the RecyclerView's ViewHolder
     * Inflates a pet icon list item with the information for a specific pet
     */
    private static class PetHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mDescriptionView;
        private final Context context;
        private final ImageView mImageView;

        public PetHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_icon_pet, parent, false));
            mNameTextView = itemView.findViewById(R.id.Pet_Name);
            mDescriptionView = itemView.findViewById(R.id.Pet_Info);
            mImageView = (ImageView) itemView.findViewById(R.id.Pet_Icon_imageView);
            context = mDescriptionView.getContext();
        }

        public void bind(SimplePet pet) {

            mNameTextView.setText(pet.getName());
            mDescriptionView.setText(pet.getImageID());
            int mImage = context.getResources().getIdentifier(pet.getImageID(), null, context.getPackageName());
            mImageView.setImageResource(mImage);
        }
    }

    private class PetAdapter extends RecyclerView.Adapter<PetHolder> {

        private final List<SimplePet> mPets;
        private final View.OnClickListener mOnClickListener;

        public PetAdapter(List<SimplePet> pets, View.OnClickListener onClickListener) {
            mPets = pets;
            mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PetHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PetHolder holder, int position) {
            SimplePet pet = mPets.get(position);
            holder.bind(pet);
            holder.itemView.setTag(pet.getID());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mPets.size();
        }
    }
}