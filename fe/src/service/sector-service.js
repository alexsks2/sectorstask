import axios from "axios";

const API_URL = 'http://localhost:8080/api/sector';

class SectorService {

    getSectors() {
        return axios.get(API_URL)
    }
}

export default new SectorService;