import './App.css';
import React, {useState} from "react";

const tableStyle = {
    borderCollapse: 'collapse',
    margin: 'auto'
}

const tdStyle = {
    border: '1px solid black',
};

const App = () => {

    // const x = () => {
    //     xhr.open("GET", url);
    //     xhr.send();
    // }
    // const xhr = new XMLHttpRequest();
    // const url = "http://localhost:3000/api/authors";



    const refresh = () => {
        fetch('http://localhost:3002/api/authors', {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }
        )
            .then(response => response.json())
            .then(json => setRowsData(json));
        console.log('hello')
    }

    async function f() {
        const response =
        await fetch('http://localhost:3002/api/authors', {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        },
    json => response.json(),
    json => setRowsData(json)
        )
    }







    const [rowsData, setRowsData] = useState(
        [{id: ' ', name: ' ', birthDate: ' ', country: ' '}])

    const deleteTableRows = () => {
        const rows = [...rowsData];
        rows.splice(rowsData.id, 1);
        setRowsData(rows);
    }

    const handleChange = (index, event) => {

        const {name, value} = event.target;
        const rowsInput = [...rowsData];
        rowsInput[index][name] = value;
        setRowsData(rowsInput);
    }

    const addTableRows = () => {
        setRowsData([...rowsData, {id: ' ', name: ' ', country: ' ', birthDate: ' '}])
    }


    return(
        <div className="container">
            <div className="row">
                <div className="col-sm-8">
                    <table>
                    <tbody className="table" style={tableStyle}>

                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Date of Birth</th>
                            <th>Country</th>
                            <th><button  onClick={addTableRows} >+</button></th>
                            <button className="btn btn-outline-danger" onClick={()=>(deleteTableRows(rowsData.id))}>x</button>
                            <button onClick={response}>Refresh</button>

                        </tr>
                        {rowsData.map(rowsData=>
                            <tr key={rowsData.id}>
                                <td style={tdStyle}>{rowsData.id}</td>
                                <td style={tdStyle}>{rowsData.name}</td>
                                <td style={tdStyle}>{rowsData.birthDate}</td>
                                <td style={tdStyle}>{rowsData.country}</td>
                            </tr>

                        )}
                        <td style={tdStyle}>
                            <input type="text" value={rowsData.name} onChange={(event)=>(handleChange(event))}
                                   name="fullName" className="form-control"/>
                        </td>

                        <td style={tdStyle}><input type="text" value={rowsData.id} onChange={(event)=>(handleChange(event))}
                                                   name="id" className="form-control"/>

                        </td>

                        <td style={tdStyle}><input type="text" value={rowsData.birthDate} onChange={(event)=>(handleChange(event))}
                                                   name="dateOfBirth" className="form-control"/>
                        </td>

                        <td style={tdStyle}><input type="text" value={rowsData.country} onChange={(event)=>(handleChange(event))}
                                                   name="country" className="form-control"/>
                        </td>
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}





export default App
