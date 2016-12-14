package com.example.michael.tddc73project;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a class for creating a signUpForm. The class consists of
 * three constructors and also methods for adding other and different
 * to the form. This is so that the form can be custom made depending
 * depending on what the user wants.
 *
 * @author Michael Sjöström
 * @author Rickard Lindstedt
 * @see ArrayList
 * @see HashMap
 * @see Map
 * @see LinearLayout
 */

public class SignUpForm extends LinearLayout {
    /**
     * This is parameters that can be changed and or added during runtime, by the user.
     * For example, the formValues contains all the information that the end-user has filled into
     * the form. The Edittexts/RadioGroups/RadioButtons are stuff that the user can add depending
     * on what is required for the form.
     */
    private LinearLayout mainll;
    private HashMap<EditText, Boolean> map;
    private ArrayList<String> formValues = new ArrayList<String>();
    private String required = " *";
    private Button save;
    private EditText fullNameField, emailField;
    private RadioGroup genderRadio;
    private RadioButton male, female, other;
    private Drawable originalDrawable;
    private OnSaveListener onSaveListener;


    /**
     * Constructor if the form is enabled programmatically.
     * @param ctx the curent context of the application
     */
    public SignUpForm(Context ctx) {
        super(ctx);
        init();
    }

    /**
     * Constructor if the form is enabled using XML.
     * @param ctx the curent context of the application
     * @param attrs additional attributes
     */
    public SignUpForm(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init();
    }

    /**
     * Constructor if the form has a defStyle defined
     * @param ctx the current context of the application
     * @param attrs additional attributes
     * @param defStyle a default style to apply to the view
     */
    public SignUpForm(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        init();
    }

    /**
     * Private function that set's up the default look of the form. It creates instances of mainll and map
     * and adds them to the view. It also adds the save button to the default form.
     */
    private void init() {
        mainll = new LinearLayout(getContext());
        mainll.setId(View.generateViewId());
        mainll.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        mainll.setOrientation(LinearLayout.VERTICAL);

        map = new HashMap<>();

        EditText temp = new EditText(getContext());
        originalDrawable = temp.getBackground();

        RelativeLayout mainrl = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.BELOW, mainll.getId());

        save = new Button(getContext());

        save.setLayoutParams(params);
        save.setText("save");

        mainrl.addView(mainll);
        mainrl.addView(save);
        addView(mainrl);
    }

    /**
     * This is a private function to validate all the required fields.
     * @return the boolean value if all the fields are validated.
     */
    private boolean checkAllFields() {
        formValues.clear();

        if (genderRadio != null) {
            RadioButton gender = (RadioButton) findViewById(genderRadio.getCheckedRadioButtonId());
            formValues.add(gender.getText().toString());
        }

        boolean shouldPassToActivity = true;
        for(Map.Entry<EditText, Boolean> entry : map.entrySet()) {
            if (!checkFieldColor(entry.getKey(), entry.getValue())) shouldPassToActivity = false;
            if (shouldPassToActivity) formValues.add(entry.getKey().getText().toString());
        }

        return shouldPassToActivity;
    }

    /**
     * Private function to validate the field that's passed in and also changes the color of the field
     * if there is an error.
     * @param et the field that should be checked
     * @param bool if the field is compulsory
     * @return if the field is OK or NOT
     */
    private boolean checkFieldColor(EditText et, Boolean bool) {

        if(et.getHint().toString().contains("Email") && bool && !isValidEmail(et.getText().toString())) {
            et.setBackground(getResources().getDrawable(R.color.progressWeak));
            return false;

        }
        else if(et.getText().length() == 0 && bool == true) {
            et.setBackgroundColor(Color.RED);
            return false;
        } else
            return true;
    }

    /**
     * Private function to validate a email
     * @param target email address to validate
     * @return if the email address is valid or not
     */
    private final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    /**
     * Private function to create listener for the inputfields in the form.
     * @param et the edittext that should add a textListener
     */
    private void addTextChangeListener(final EditText et) {

        final Drawable orgDrawable = et.getBackground();

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    et.setBackgroundDrawable(orgDrawable);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    /**
     * This is a public method to set a listener to the save button, the button that always
     * is created.
     * @param listener the interface that keeps the onSave function.
     */
    public void setOnSave(OnSaveListener listener) {
        onSaveListener = listener;

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAllFields()) onSaveListener.onSave(formValues);
            }
        });
    }

    /**
     * Public function to create a name field in the form
     * @param compulsory true if the field should be required
     */
    public void addNameField(final boolean compulsory) {
        LinearLayout ll1 = new LinearLayout(getContext());
        ll1.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        fullNameField = new EditText(getContext());

        if(compulsory)
            addTextChangeListener(fullNameField);

        String req = compulsory ? required : "";

        fullNameField.setHint("Full name" + req);
        fullNameField.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT));

        ll1.addView(fullNameField);
        mainll.addView(ll1);
        map.put(fullNameField, compulsory);
    }

    /**
     * Public function to create a password field in the form
     * @param compulsory true if the field should be required
     */
    public void addPasswordField( boolean compulsory) {
        PasswordStrength ps = new PasswordStrength(getContext());

        EditText passwordField = ps.getPasswordField();

        if(compulsory)
            addTextChangeListener(passwordField);

        String req = compulsory ? required : "";

        ps.addPasswordFieldText(req);

        //password.setHint("Password" + req);
        //password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        mainll.addView(ps);
        map.put(passwordField, compulsory);
    }

    /**
     * Public function to create a email field in the form
     * @param compulsory true if the field should be required
     */
    public void addEmailField(boolean compulsory) {

        EditText emailField = new EditText(getContext());
        emailField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        if (compulsory)
            addTextChangeListener(emailField);

        String req = compulsory ? required : "";

        emailField.setHint("Email" + req);

        mainll.addView(emailField);
        map.put(emailField, compulsory);
    }

    /**
     * Public function to create a gender radiogroup in the form
     * @param compulsory true if the field should be required
     */
    public void addGender(boolean compulsory) {
        genderRadio = new RadioGroup(getContext());
        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);

        TextView gender = new TextView(getContext());
        gender.setTextSize(20);

        gender.setPadding(0, 15, 0, 0);

        String req = compulsory ? required : "";
        gender.setText("Gender:" + req);

        genderRadio.setOrientation(RadioGroup.HORIZONTAL);

        male = new RadioButton(getContext());
        male.setText("Male");
        male.setId(View.generateViewId());

        female = new RadioButton(getContext());
        female.setText("Female");

        other = new RadioButton(getContext());
        other.setText("Other");

        genderRadio.addView(male);
        genderRadio.addView(female);
        genderRadio.addView(other);

        genderRadio.check(male.getId());

        ll.addView(gender);
        ll.addView(genderRadio);

        mainll.addView(ll);
    }
}
