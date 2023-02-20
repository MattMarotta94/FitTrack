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

        this.bindClassMethods(['mount', 'search', 'displayWorkout', 'showEdits', 'submitEdits', 'submitDelete', 'submitCancel', 'cancel'], this);

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
        document.getElementById('cancel-edit').addEventListener('click', this.submitCancel);
        document.getElementById('cancel').addEventListener('click', this.cancel);
        
        this.header.addHeaderToPage();

        this.client = new FitTrackClient();
    }

    async search(evt) {
        evt.preventDefault();

        const searchButton = document.getElementById('search-button');
        const origButtonText = searchButton.innerText;
        searchButton.innerText = 'Searching...';

        const searchCriteria = document.getElementById('search-criteria').value;
        const results = await this.client.getWorkout(searchCriteria);

        searchButton.innerText = origButtonText;

        this.dataStore.set('results', results)
        
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
        document.getElementById('workout-exercises').innerText = workout.exercises;
    }

    showEdits() {
        console.log("Hit Show Edits");
        const workout = this.dataStore.get('results');
        console.log("workout", workout);
        document.getElementById('date').value = workout.date;
        document.getElementById('name').value = workout.name;
        document.getElementById('exercises').value = workout.exercises;
        document.getElementById('notes').value = workout.notes;
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
        submitButton.innerText = 'Making Changes...';

        const workoutName = document.getElementById('name').value;
        const workoutDate = document.getElementById('date').value;
        const workoutNotes = document.getElementById('notes').value;
        const workoutExercises = document.getElementById('exercises').value;

        const updatedWorkout = await this.client.updateWorkout(workoutDate, workoutName, workoutExercises, workoutNotes,  (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('results', updatedWorkout);

        const editsFields = document.getElementById('edit-fields');
        editsFields.classList.add('hidden');
       
    }

    async submitDelete() {
        
       const workout = this.dataStore.get('results')

       const deleteButton = document.getElementById('delete-button');
       const origButtonText = deleteButton.innerText;
       deleteButton.innerText = 'Deleting..';

       const workoutDate = workout.date;

       const deletedWorkout =  await this.client.deleteWorkout(workoutDate, (error) => {
           errorMessageDisplay.innerText = `Error: ${error.message}`;
           errorMessageDisplay.classList.remove('hidden');
       });

       window.location.href = `/getWorkout.html`;

    this.dataStore.set('results', deletedWorkout);
    }

    submitCancel(evt) {
        evt.preventDefault();
        var editFields = document.getElementById('edit-fields');
        editFields.classList.add('hidden');
    }

    cancel(evt) {
        evt.preventDefault();
        window.location.href = `/index.html`;
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