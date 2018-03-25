
package com.quoders.app.codewarschallenge.data.network.model.users;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Languages {

    @SerializedName("kotlin")
    private Kotlin Kotlin;
    @SerializedName("objc")
    private Objc Objc;
    @SerializedName("ocaml")
    private Ocaml Ocaml;

    public Kotlin getKotlin() {
        return Kotlin;
    }

    public void setKotlin(Kotlin kotlin) {
        Kotlin = kotlin;
    }

    public Objc getObjc() {
        return Objc;
    }

    public void setObjc(Objc objc) {
        Objc = objc;
    }

    public Ocaml getOcaml() {
        return Ocaml;
    }

    public void setOcaml(Ocaml ocaml) {
        Ocaml = ocaml;
    }

}
