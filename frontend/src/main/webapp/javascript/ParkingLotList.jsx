import React, {Component} from "react";
import {Button, ButtonToolbar, Col, FormControl, FormGroup, Grid, Row,} from 'react-bootstrap/lib';
import Pagination from "react-js-pagination";
import BackendApi from './BackendApi';

class ParkingLotList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            content: [],
            name: '',
            address: '',
            tel: '',
            activePage: 1,
            totalPages: null,
            itemsCountPerPage: null,
            totalItemsCount: null
        };

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleAddressChange = this.handleAddressChange.bind(this);
        this.handleTelChange = this.handleTelChange.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.clickSearchButton = this.clickSearchButton.bind(this);
    }

    componentDidMount() {
        this.searchParkingLots("sort=basicParkingFee,asc");
    }

    handleNameChange(e) {
        this.setState({name: e.target.value});
    }

    handleAddressChange(e) {
        this.setState({address: e.target.value});
    }

    handleTelChange(e) {
        this.setState({tel: e.target.value});
    }

    handlePageChange(pageNumber) {
        this.setState({activePage: pageNumber});
        this.searchDataWithApi(pageNumber);
    }

    clickSearchButton() {
        this.setState({activePage: 1});
        this.searchDataWithApi(1);
    }

    searchDataWithApi(pageNumber) {
        this.searchParkingLots("name=" + this.state.name + "&address=" + this.state.address + "&tel=" + this.state.tel + "&page=" + (pageNumber - 1) + "&sort=basicParkingFee,asc")
    }

    searchParkingLots(parameters) {
        return BackendApi.search(parameters)
            .then(responseJson => this.setState({
                content: responseJson.content,
                totalPages: responseJson.totalPages,
                itemsCountPerPage: responseJson.size,
                totalItemsCount: responseJson.totalElements
            }))
            .catch(error => {
                alert(error.message);
            })
    }

    render() {
        const items = this.state.content.map((parkingLot) => {
                return (
                    <tr key={parkingLot.code}>
                        <td>{parkingLot.name}</td>
                        <td>{parkingLot.address}</td>
                        <td>{parkingLot.tel}</td>
                        <td>{parkingLot.basicParkingFee}</td>
                        <td>{parkingLot.weekdayOpeningTime}</td>
                        <td>{parkingLot.weekdayClosingTime}</td>
                        <td>{parkingLot.availableNow ? "TRUE" : "FALSE"}</td>
                    </tr>
                );
            }
        );

        return (
            <Grid fluid={true}>
                <Row style={{marginTop: "3%"}}>
                    <Col>
                        <FormGroup>
                            <FormControl
                                placeholder="name"
                                aria-label="name"
                                value={this.state.name}
                                onChange={this.handleNameChange}
                                onKeyPress={event => {
                                    if (event.key === 'Enter')
                                        this.clickSearchButton();
                                }}
                                aria-describedby="basic-addon1"
                            />
                            <FormControl
                                placeholder="address"
                                aria-label="address"
                                value={this.state.address}
                                onChange={this.handleAddressChange}
                                onKeyPress={event => {
                                    if (event.key === 'Enter')
                                        this.clickSearchButton();
                                }}
                                aria-describedby="basic-addon1"
                            />
                            <FormControl
                                placeholder="tel"
                                aria-label="tel"
                                value={this.state.tel}
                                onChange={this.handleTelChange}
                                onKeyPress={event => {
                                    if (event.key === 'Enter')
                                        this.clickSearchButton();
                                }}
                                aria-describedby="basic-addon1"
                            />
                        </FormGroup>
                        <ButtonToolbar>
                            <Button className="btn btn-primary"
                                    onClick={this.clickSearchButton.bind(this)}>Search</Button>
                        </ButtonToolbar>
                    </Col>
                </Row>
                <Row style={{marginTop: "3%"}}>
                    <Col>
                        <table className="table">
                            <thead>
                            <tr>
                                <th scope="col" className="col-md-2">name</th>
                                <th scope="col" className="col-md-2">address</th>
                                <th scope="col" className="col-md-1">tel</th>
                                <th scope="col" className="col-md-1">price</th>
                                <th scope="col" className="col-md-1">OpeningHour</th>
                                <th scope="col" className="col-md-1">ClosingHour</th>
                                <th scope="col" className="col-md-1">availableNow</th>
                            </tr>
                            </thead>
                            <tbody>
                            {items}
                            </tbody>
                        </table>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <div className="d-flex justify-content-center">
                            <Pagination
                                hideNavigation
                                activePage={this.state.activePage}
                                itemsCountPerPage={this.state.itemsCountPerPage}
                                totalItemsCount={this.state.totalItemsCount}
                                pageRangeDisplayed={10}
                                itemClass='page-item'
                                linkClass='btn btn-light'
                                onChange={this.handlePageChange}
                            />
                        </div>
                    </Col>
                </Row>
            </Grid>
        );
    }
}

export default ParkingLotList;