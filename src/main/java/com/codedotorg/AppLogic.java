package com.codedotorg;

import com.codedotorg.modelmanager.CameraController;

public class AppLogic {

    /** The pin to unlock the app */
    private String pin;

    /** The pin the user has provided */
    private String user;
    private boolean isUnlocked;  // Whether the app is unlocked
    

    /**
     * Constructor for the AppLogic class.
     * Initializes the pin with a random value and user with an empty string.
     */
    public AppLogic() {
        pin = createRandomPin();
        user = "";
    }

    /**
     * Creates a user PIN based on the predicted class.
     * @param predictedClass the predicted class from the machine learning model
     * @return the user PIN as a string
     */
    
        public String createUserPin(CameraController predictedClass) {
           
            if (predictedClass.getPredictedClass().equals("Correct Face") && predictedClass.getPredictedScore() > .80) {
                isUnlocked = true;
            } else if (predictedClass.getPredictedClass().equals("Wrong Face") || (predictedClass.getPredictedClass().equals("No Face"))) {
                isUnlocked = false;
            } else if (predictedClass.getPredictedClass().equals("Correct Face") && predictedClass.getPredictedScore() < .80) {
                isUnlocked = false;
            } else {
                isUnlocked = false;
            }
            return pin;
        }
    

    /**
     * Checks if the length of the user's PIN is equal to 4.
     * @return true if the length of the user's PIN is equal to 4, false otherwise.
     */
    public boolean checkPinLength() {
        return pin.length() == 4;
    }

    /**
     * Returns the status of the user's input PIN.
     * @param userPin the PIN entered by the user
     * @return a string indicating whether the PIN is correct or not
     */
    // public String getPinStatus(String userPin) {
    //     if (userPin.equals(pin)) {
    //         return "Unlocked";
    //     } else {
    //         return "Locked";
    //     }
    // }
    

    public String getFaceStatus(CameraController predictedClass) {
        if (predictedClass.getPredictedClass().trim().equals("0 Correct Face") && predictedClass.getPredictedScore() > .97) {
            return "Unlocked";
        } else {
            return "Locked";
        }
    }
    /**
     * Resets the logic of the application by generating
     * a new random PIN and clearing the user field.
     */
    public void resetLogic() {
        pin = createRandomPin();
        user = "";
    }

    /**
     * Generates a random 4-digit PIN number.
     * @return the generated PIN number as a string.
     */
    private String createRandomPin() {
        int pin = (int)(Math.random() * 9000) + 1000; // This will generate a random four digit number
        return String.valueOf(pin);
    }


    public boolean isAppUnlocked() {
        return isUnlocked;
    }
}
