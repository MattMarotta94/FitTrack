import FitTrackClient from '../api/fitTrackClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


class HomePage extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'searchCardio', 'searchCalisthenics', 'searchWeightLifting'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        document.getElementById('cardio-button').addEventListener('click', this.searchCardio);
        document.getElementById('weightlifting-button').addEventListener('click', this.searchWeightLifting);
        document.getElementById('calisthenics-button').addEventListener('click', this.searchCalisthenics);
        
        this.client = new FitTrackClient();
    }


async searchCardio(evt) {
    evt.preventDefault();

    const Cardio = "Cardio";

    const jsonCardioList = await this.client.getExercises(`${Cardio}`);
    console.log(jsonCardioList);
    
    for (var i = 0; i < jsonCardioList.length; i++) {
        var exercise = jsonCardioList[i];
        var name = exercise.name;
        document.getElementById("cardioList").innerHTML += "<br>" + name + "</br>"
        }
    }

async searchCalisthenics(evt) {
    evt.preventDefault();

    const Calisthenics = "Calisthenics";

    const jsonCalisthenicsList = await this.client.getExercises(`${Calisthenics}`);
    console.log(jsonCalisthenicsList);
    
    for (var i = 0; i < jsonCalisthenicsList.length; i++) {
        var exercise = jsonCalisthenicsList[i];
        var name = exercise.name;
        document.getElementById("calisthenicsList").innerHTML += "<br>" + name + "</br>"
    }

    }

async searchWeightLifting(evt) {
    evt.preventDefault();

    const WeightLifting = "WeightLifting";

    const jsonWeightLiftingList = await this.client.getExercises(`${WeightLifting}`);
    console.log(jsonWeightLiftingList);
    
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