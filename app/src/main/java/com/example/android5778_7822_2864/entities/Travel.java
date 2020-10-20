package com.example.android5778_7822_2864.entities;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;


public class Travel {
    public  enum TravelStatus{ FREE//To change to free
        , IN_THERAPY , FINISHED }

        Long id;
        Long idOfDriver;
        TravelStatus myTravelStatus;
        String source;
        String destination;
        String firstHour;
        String lastHour;
        String name;
        String phone;
        String email;

        /**
         * constractor
         * @param myTravelStatus
         * @param source
         * @param destination
         * @param firstHour
         * @param lastHour
         * @param name
         * @param phone
         * @param email
         */
        public Travel(Long myId,Long myIdOfDriver,TravelStatus myTravelStatus, String source, String destination, String firstHour, String lastHour, String name, String phone, String email) {
            this.id=myId;
            this.idOfDriver=myIdOfDriver;
            this.myTravelStatus = myTravelStatus;
            this.source = source;
            this.destination = destination;
            this.firstHour = firstHour;
            this.lastHour = lastHour;
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        /**
         * defulte constractor
         */
        public Travel() {
            this.id= new Long(0);
            this.idOfDriver=new Long(0);
            this.myTravelStatus = TravelStatus.FREE;
            this.source = "";
            this.destination = "";
            // this.firstHour =new Time(0,0,0);
            // this.lastHour = new Time(0,0,0);
            this.firstHour="00:00";
            this.lastHour="00:00";
            this.name = "";
            this.phone = "0";
            this.email = "";
        }

        /**
         * getter id
         * @return id
         */
        public Long getId() {
            return id;
        }

        /**
         * setter id
         * @param id
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * gettert id  of driver
         * @return
         */
        public Long getIdOfDriver() {
            return idOfDriver;
        }

        /**
         *
         * setter id of driver
         * @param idOfDriver
         */
        public void setIdOfDriver(Long idOfDriver) {
            this.idOfDriver = idOfDriver;
        }
        /**
         * geteer status
         * @return status
         */
        public TravelStatus getMyTravelStatus() {
            return myTravelStatus;
        }

        /**
         * setter status
         * @param myTravelStatus
         */
        public void setMyTravelStatus(TravelStatus myTravelStatus) {
            this.myTravelStatus = myTravelStatus;
        }

        /**
         * getter source
         * @return source
         */
        public String getSource() {
            return source;
        }

        /**
         *setter status
         * @param source
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         * getter destination
         * @return destination
         */
        public String getDestination() {
            return destination;
        }

        /**
         * setter destination
         * @param destination
         */
        public void setDestination(String destination) {
            this.destination = destination;
        }

        /**
         * getter first hour
         * @return first hour
         */
        public String getFirstHour() {
            return firstHour;
        }

        /**
         * setter first hour
         * @param firstHour
         */
        public void setFirstHour(String firstHour) {
            this.firstHour = firstHour;
        }

        /**
         * getter last hour
         * @return last hour
         */
        public String getLastHour() {
            return lastHour;
        }

        /**
         * setter last hour
         * @param lastHour
         */
        public void setLastHour(String lastHour) {
            this.lastHour = lastHour;
        }

        /**
         * getter name
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * setter name
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * getter phone
         * @return phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * setter phone
         * @param phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * getter email
         * @return email
         */
        public String getEmail() {
            return email;
        }

        /**
         * setter email
         * @param email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * to string
         *
         * @return string
         */
        @Override
        public String toString() {
            return "Travel{" +
                    "id=" + id +
                    ", idofDriver=" + idOfDriver +
                    ", myTravelStatus=" + myTravelStatus +
                    ", source='" + source + '\'' +
                    ", destination='" + destination + '\'' +
                    ", firstHour='" + firstHour + '\'' +
                    ", lastHour='" + lastHour + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
        /**
         * equals between two object from kind travel
         * @param o
         * @return true or false
         */
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Travel travel = (Travel) o;
            return Objects.equals(id, travel.id) &&
                    Objects.equals(idOfDriver, travel.idOfDriver) &&
                    myTravelStatus == travel.myTravelStatus &&
                    Objects.equals(source, travel.source) &&
                    Objects.equals(destination, travel.destination) &&
                    Objects.equals(firstHour, travel.firstHour) &&
                    Objects.equals(lastHour, travel.lastHour) &&
                    Objects.equals(name, travel.name) &&
                    Objects.equals(phone, travel.phone) &&
                    Objects.equals(email, travel.email);
        }



}


