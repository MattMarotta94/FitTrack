import FitTrackClient from '../api/fitTrackClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the home page of the website.
 */
class HomePage extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'searchCardio', 'searchCalisthenics', 'searchWeightLifting'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Adds the header to the page.
     * Adds event listeners to buttons.
     * Loads the FitTrack Client.
     */
    mount() {
        this.header.addHeaderToPage();
        document.getElementById('cardio-button').addEventListener('click', this.searchCardio);
        document.getElementById('weightlifting-button').addEventListener('click', this.searchWeightLifting);
        document.getElementById('calisthenics-button').addEventListener('click', this.searchCalisthenics);

        this.client = new FitTrackClient();
    }

    /**
     * Method to search Cardio Exercises using the getExercises endpoint.
     * @param {*} evt 
     */
    async searchCardio(evt) {
        evt.preventDefault();
        const cardioButton = document.getElementById('cardio-button');
        const origButtonText = cardioButton.innerText;
        cardioButton.innerText = "Loading..";

        const Cardio = "Cardio";
        const jsonCardioList = await this.client.getExercises(`${Cardio}`);
        console.log(jsonCardioList);

        cardioButton.innerText = origButtonText;
        document.getElementById('cardioList').innerHTML="";
        
            for (var i = 0; i < jsonCardioList.length; i++) {
                var exercise = jsonCardioList[i];
                var name = exercise.name;
                document.getElementById("cardioList").innerHTML += "<br>" + name + "</br>"
                }

        
                
        }

    /**
     * Method to search Calisthenics exercises using the getExercises endpoint.
     * @param {*} evt 
     */    
    async searchCalisthenics(evt) {
        evt.preventDefault();
        const calisthenicsButton = document.getElementById('calisthenics-button');
        const origButtonText = calisthenicsButton.innerText;
        calisthenicsButton.innerText = "Loading..";

        const Calisthenics = "Calisthenics";
        const jsonCalisthenicsList = await this.client.getExercises(`${Calisthenics}`);
        console.log(jsonCalisthenicsList);

        calisthenicsButton.innerText = origButtonText;
        document.getElementById('calisthenicsList').innerHTML="";
        
            for (var i = 0; i < jsonCalisthenicsList.length; i++) {
                var exercise = jsonCalisthenicsList[i];
                var name = exercise.name;
                document.getElementById("calisthenicsList").innerHTML += "<br>" + name + "</br>"
            }

        }

    /**
     * Method to search Weightlifting exercises using the getExercises endpoint.
     * @param {*} evt 
     */    
    async searchWeightLifting(evt) {
        evt.preventDefault();
        const weightliftingButton = document.getElementById('weightlifting-button');
        const origButtonText = weightliftingButton.innerText;
        weightliftingButton.innerText = "Loading..";

        const WeightLifting = "WeightLifting";
        const jsonWeightLiftingList = await this.client.getExercises(`${WeightLifting}`);
        console.log(jsonWeightLiftingList);

        weightliftingButton.innerText = origButtonText;
        document.getElementById('weightLiftingList').innerHTML="";
        
            for (var i = 0; i < jsonWeightLiftingList.length; i++) {
                var exercise = jsonWeightLiftingList[i];
                var name = exercise.name;
                document.getElementById("weightLiftingList").innerHTML += "<br>" + name + "</br>"
            }

        }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);