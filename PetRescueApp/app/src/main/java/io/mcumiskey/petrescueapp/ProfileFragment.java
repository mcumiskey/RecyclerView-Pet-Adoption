package io.mcumiskey.petrescueapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private SimplePet mPet;
    public static final String ARG_PET_ID = "pet_id";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        int petID = 1;

        // Get the pet ID from the fragment arguments
        Bundle args = getArguments();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            petID = args.getInt(ARG_PET_ID);
        }
        // Get the selected band
        mPet = PetRepository.getInstance(requireContext()).getPet(petID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        if (mPet != null) {
            TextView nameTextView = rootView.findViewById(R.id.petName_textView);
            nameTextView.setText(mPet.getName());
        }

        return rootView;
    }
}