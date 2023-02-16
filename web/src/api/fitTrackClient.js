import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call FitTrack
 */
export default class FitTrackClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = [`clientLoaded`, 'getIdentity', 'login', 'logout', 'createWorkout', 'getWorkout', 'getExercises'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

        /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
        clientLoaded() {
            if (this.props.hasOwnProperty("onReady")) {
                this.props.onReady(this);
            }
        }

       /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }
/**
 * Create a new workout owned by the current user.
 * @param {*} name the name of the workout to create.
 * @param {*} date the date the workout was created.
 * @param {*} notes any notes the user adds to the workout.
 * @param {*} errorCallback (Optional) A function to execute if the call fails.
 * @returns The workout that has been created.
 */
    async createWorkout(name, date, notes, exercises, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create workouts.");
            const response = await this.axiosClient.post('workouts', {
                name:name,
                notes:notes,
                date:date,
                exercises:exercises
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.workout;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getWorkout(date, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create workouts.");
            const response = await this.axiosClient.get(`workouts/${date}`, { headers: {
                Authorization: `Bearer ${token}`
            }});
            return response.data.workout;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async updateWorkout(date, name, notes, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can edit workouts.");
            const response = await this.axiosClient.put(`workouts/${date}`, {
                name:name,
                notes:notes,
                date:date
            },
            { headers: {
                Authorization: `Bearer ${token}`
            }});
            return response.data.workout;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async deleteWorkout(date, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete workouts.");
            const response = await this.axiosClient.delete(`workouts/${date}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.workout;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async getExercises(type, errorCallback) {
        try {
            const response = await this.axiosClient.get(`exercises/${type}`)
            return response.data.exerciseList;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }
  
        /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
        handleError(error, errorCallback) {
            console.error(error);
    
            const errorFromApi = error?.response?.data?.error_message;
            if (errorFromApi) {
                console.error(errorFromApi)
                error.message = errorFromApi;
            }
    
            if (errorCallback) {
                errorCallback(error);
            }
        }
}