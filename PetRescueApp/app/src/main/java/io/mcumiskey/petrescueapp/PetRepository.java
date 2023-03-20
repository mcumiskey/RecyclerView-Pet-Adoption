package io.mcumiskey.petrescueapp;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class PetRepository {
    private static PetRepository instance;
    private List<SimplePet> mPets;

    public static PetRepository getInstance(Context context) {
        if (instance == null) {
            instance = new PetRepository(context);
        }
        return instance;
    }

    private PetRepository(Context context) {
        mPets = new ArrayList<>();

        Resources res = context.getResources();
        String[] petnames = res.getStringArray(R.array.pet_names);
        String[] images = res.getStringArray(R.array.photo_ids);

        for (int i = 0; i < petnames.length; i++) {
            mPets.add(new SimplePet(i + 1, petnames[i], images[i], false));
        }
    }

    public List<SimplePet> getPets() {
        return mPets;
    }

    public SimplePet getPet(int petId) {
        for (SimplePet pet : mPets) {
            if (pet.getID() == petId) {
                return pet;
            }
        }
        return null;
    }
}
