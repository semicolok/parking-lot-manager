import React, { Component } from "react";
import ReactDOM from 'react-dom';

import ParkingLotList from './ParkingLotList';

class Main extends Component {
    render() {
        return (
            <div className="container">
                <h1>Parking Lot Management System</h1>
                <ParkingLotList />
            </div>
        );
    }
}

ReactDOM.render(
    <Main />,
    document.getElementById('react-app')
);