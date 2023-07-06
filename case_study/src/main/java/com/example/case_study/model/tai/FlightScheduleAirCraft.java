package com.example.case_study.model.tai;

import javax.persistence.*;

    @Entity
    @Table(name = "flight_schedule_aircraft")
    public class FlightScheduleAirCraft {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "flightSchedule_id")
        private FlightSchedule flightSchedule;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "air_craft_id")
        private AirCraft idAirCraft;
        private boolean flag;
        public FlightScheduleAirCraft() {
        }

        public FlightScheduleAirCraft(Integer id, FlightSchedule flightSchedule, AirCraft idAirCraft) {
            this.id = id;
            this.flightSchedule = flightSchedule;
            this.idAirCraft = idAirCraft;
        }

        public FlightScheduleAirCraft(Integer id, FlightSchedule flightSchedule, AirCraft idAirCraft, boolean flag) {
            this.id = id;
            this.flightSchedule = flightSchedule;
            this.idAirCraft = idAirCraft;
            this.flag = flag;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer idFlightScheduleAirCraft) {
            this.id = idFlightScheduleAirCraft;
        }

        public FlightSchedule getFlightSchedule() {
            return flightSchedule;
        }

        public void setFlightSchedule(FlightSchedule flightSchedule) {
            this.flightSchedule = flightSchedule;
        }

        public AirCraft getIdAirCraft() {
            return idAirCraft;
        }

        public void setIdAirCraft(AirCraft idAirCraft) {
            this.idAirCraft = idAirCraft;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
