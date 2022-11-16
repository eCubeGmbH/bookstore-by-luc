import './App.css';
import React, {useState} from "react";
import {render} from "react-dom";

const tableStyle = {
    borderCollapse: 'collapse',
    margin: 'auto'
}

const tdStyle = {
    border: '1px solid black',
};

const App = () => {

    const refresh = () => {
        fetch('http://localhost:9988/api/authors', {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }
        )
            .then(response => response.json())
            .then(json => setRowsData(json));
        console.log('hello from getAll')
    }

    const [rowsData, setRowsData] = useState(
        [{id: ' ', name: ' ', birthDate: ' ', country: ' '}]
    )

    const deleteTableRows = (id) => {
        fetch('http://localhost:9988/api/authors/' + id, {
                method: 'DELETE',
            }
        )   .then(() => console.log("Deletion completed"))
    }

    const removeElement = (id) => {
        const newTable = rowsData.filter(
            (rowData) => rowData.id !== id
        );
        setRowsData(newTable);
    }

    const x = (id) => {
        removeElement(id);
        deleteTableRows(id)
    }


    const add = () => {
        fetch('http://localhost:9988/api/authors', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstParam: rowsData.id,
                    secondParam: <input defaultValue={'Name goes here!'} value={''}/>
                })
            }
        )   .then(response => response.json())
            .then(json => setRowsData(json))
        console.log('hello from add')
    }

    const addTableRows = () => {
        setRowsData([...rowsData, {id: ' ', name: ' ', country: ' ', birthDate: ' '}])
    }




    const [editedId, setEditedId] = useState('')

    return(
        <div className="container">
            <div className="row">
                <div className="col-sm-8">
                    <table style={tableStyle}>
                    <tbody className="table" style={tableStyle}>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Date of Birth</th>
                            <th>Country</th>
                            <th><button  onClick={add} >+</button></th>
                            <button onClick={refresh}>Refresh</button>
                        </tr>
                        {rowsData.map(rowsData =>
                            <tr key={rowsData.id}>
                                <td style={tdStyle}>{rowsData.id}</td>
                                <td style={tdStyle}>{editedId === rowsData.id ? <input defaultValue={'Max Mustermann'}/> : rowsData.name}</td>
                                <td style={tdStyle}>{editedId === rowsData.id ? <input defaultValue={'2022-11-11'}/> : rowsData.birthDate}</td>
                                <td style={tdStyle}>{editedId === rowsData.id ? <input defaultValue={'DE'}/> : rowsData.country}</td>
                                <td>
                                    <button onClick={() => {setEditedId(rowsData.id)}}>Edit</button>
                                    <button onClick={() => {setEditedId('')}}>Cancel</button>
                                    <button  onClick={addTableRows} >+</button>
                                    <button onClick={() => x(rowsData.id)} >x</button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default App