package org.example;

import java.util.Objects;

public  class Friend implements Comparable<Friend> {

    //All fields accepting string arguments...

    private String first_name;   //friend name private...
    private String last_name;
    private String Phn_num;     //Phone Number field
    private String email_id;     //Email id field
    private String address;      //Address field
    private String D_O_B;        //Date of birth field

    public Friend() {

        //No argument constructor for defining the new fields as Null...
        first_name=null;
        last_name=null;
        Phn_num=null;
        email_id=null;
        address=null;
        D_O_B=null;
    }

    public Friend(String first_name,String last_name){


        // Two argument constructor for taking friends name...Made for searching, updating ,deleting...
        this.first_name=first_name;
        this.last_name=last_name;
        this.Phn_num=null;
        this.address=null;
        this.email_id=null;
        this.D_O_B=null;
    }

    /*\
    *   Getter and setter methods for all the filed...
    * */
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhn_num() {
        return Phn_num;
    }

    public void setPhn_num(String phn_num) {
        Phn_num = phn_num;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getD_O_B() {
        return D_O_B;
    }

    public void setD_O_B(String d_O_B) {
        D_O_B = d_O_B;
    }

  @Override     //Overriding the toString method for getting the desired info...
  public String  toString(){
         return primary()+"\n"+getFirst_name()+"\n"+getLast_name()+" \n"+getPhn_num()+" \n"+getEmail_id()+"\n"+getD_O_B()+"\n"+getAddress();

  }

    @Override  //Method from comparable interface...
    //Default pattern for sorting is name...
    public int compareTo(Friend o) {
        int sort=first_name.compareTo(o.first_name);
        if(sort!=0)
            return last_name.compareTo(o.last_name);
        return 0;
    }
    @Override  //Overriding equals method...
         //So that it compares two friend or searched friends by their name...
    public boolean equals(Object o){
        if(this==o)
              return true;

        if(!(o instanceof Friend))
            return false;

        if(o==null)
             return false;
        Friend fr=(Friend)o;
        return Objects.equals(first_name,fr.first_name) &&
                Objects.equals(last_name,fr.last_name);
    }

    @Override //Changing the hash code...
    public int hashCode(){
        return 5*first_name.hashCode()+2*last_name.hashCode();
    }

   public String primary()  //Generating primary key for each friend so that we can easily search for it in the database...
   {
       return "@"+first_name.substring(first_name.length()-4)+last_name.substring(0,4);
   }

}
