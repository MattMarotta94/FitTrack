import FitTrackClient from "../api/fitTrackClient";
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


class GetWorkout extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'displayWorkout'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displayWorkout);
        console.log("getWorkout constructor");
    }


    mount() {
        document.getElementById('search-workouts-form').addEventListener('submit', this.search);
        document.getElementById('search-button').addEventListener('click', this.displayWorkout);

        this.header.addHeaderToPage();

        this.client = new FitTrackClient();
    }

    async displayWorkout() {
        const workoutDate = document.getElementById('search-criteria').value;
        const workout = this.client.getWorkout(workoutDate);

        const searchButton = document.getElementById('search-button');
        const origButtonText = searchButton.innerText;
        searchButton.innerText = 'Loading...';

        document.getElementById('workout-name').innerText = workout.name;
        document.getElementById('workout-date').innerText = workout.date;
        document.getElementById('workout-notes').innerText = workout.notes;

    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const getWorkout = new GetWorkout();
    getWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);