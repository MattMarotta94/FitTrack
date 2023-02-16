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

        this.bindClassMethods(['mount', 'search', 'displayWorkout', 'showEdits', 'submitEdits', 'submitDelete'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displayWorkout);
        console.log("getWorkout constructor");
    }


    mount() {
        document.getElementById('search-button').addEventListener('click', this.search);
        document.getElementById('search-button').addEventListener('click', this.displayWorkout);
        document.getElementById('edit-button').addEventListener('click', this.showEdits);
        document.getElementById('submit-edits').addEventListener('click', this.submitEdits);
        document.getElementById('delete-button').addEventListener('click', this.submitDelete);
        
        this.header.addHeaderToPage();

        this.client = new FitTrackClient();
    }

    async search(evt) {
        // Prevent submitting the from from reloading the page.
        evt.preventDefault();

        const searchCriteria = document.getElementById('search-criteria').value;
        const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);

        // If the user didn't change the search criteria, do nothing
        if (previousSearchCriteria === searchCriteria) {
            return;
        }

        if (searchCriteria) {
            const results = await this.client.getWorkout(searchCriteria);

            this.dataStore.set('results', results)
            }
    
        document.getElementById("edits-card").classList.remove("hidden");

            
        } 

    displayWorkout() {
        const workout = this.dataStore.get('results');
        if (workout == null) {
            return
        }
        document.getElementById('workout-name').innerText = workout.name;
        document.getElementById('workout-date').innerText = workout.date;
        document.getElementById('workout-notes').innerText = workout.notes;
    }

    showEdits() {
        var editFields = document.getElementById('edit-fields');
        editFields.classList.remove('hidden');
    }

    async submitEdits(evt) {
        evt.preventDefault();
     
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = `error`;
        errorMessageDisplay.classList.add('hidden')

        const submitButton = document.getElementById('submit-edits');
        const origButtonText = submitButton.innerText;
        submitButton.innerText = 'Success!';

        const workoutName = document.getElementById('name').value;
        const workoutDate = document.getElementById('date').value;
        const workoutNotes = document.getElementById('notes').value;

        const updatedWorkout = await this.client.updateWorkout(workoutDate, workoutName, workoutNotes, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        

        this.dataStore.set('results', updatedWorkout);
       
    }

    async submitDelete() {
        
       const workout = this.dataStore.get('results')

       const deleteButton = document.getElementById('delete-button');
       const origButtonText = deleteButton.innerText;
       deleteButton.innerText = 'Deleted';

       const workoutDate = workout.date;

       const deletedWorkout =  await this.client.deleteWorkout(workoutDate, (error) => {
           errorMessageDisplay.innerText = `Error: ${error.message}`;
           errorMessageDisplay.classList.remove('hidden');
       });

    this.dataStore.set('results', deletedWorkout);
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