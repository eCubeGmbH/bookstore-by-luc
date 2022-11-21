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

    const [rowsData, setRowsData] = useState(
        [{id: ' ', name: ' ', birthDate: ' ', country: ' '}]
    )

    const [editedId, setEditedId] = useState('')

    const [addedRow, setAddedRow] = useState({id: '', name: '', country: '', birthDate: ''})

    const [rowAddingMode, setRowAddingMode] = useState(false)

    const addTableRows = () => {
        setRowAddingMode(true)
    }

    const clearState = () => {
        setAddedRow()
    };

    //getAll Method
    //TODO automate the 'refresh' process => useEffect()
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

    //delete Method
    const deleteTableRows = (id) => {
        fetch('http://localhost:9988/api/authors/' + id, {
                method: 'DELETE',
            }
        )  .then(() => {
            setRowsData(rowsData.filter(row => row.id !== id))
        })
            .then(() => console.log("Deletion completed"))
    }

    //post Method
    const add = () => {

        fetch('http://localhost:9988/api/authors', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(addedRow)
            }
        )   .then(response => response.json(), json => setRowsData(oldState => [...oldState, json]))
        console.log('hello from add')
    }

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
                            <th><button  onClick={add} >Add</button></th>
                            <th><button onClick={refresh}>Refresh</button></th>
                            <button  onClick={addTableRows} >+</button>
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
                                    <button onClick={() => deleteTableRows(rowsData.id)} >x</button>
                                </td>
                            </tr>
                        )}
                        {rowAddingMode ? //TODO convert to if Statement
                            <tr>
                                <td style={tdStyle}></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({name: e.target.value})}}/></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({country: e.target.value})}}/></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({birthDate: e.target.value})}}/></td>
                                <td>
                                    <button onClick={add}>Save</button>
                                    <button onClick={() => {setRowAddingMode(false)}}>Cancel</button>
                                </td>
                            </tr> : <br/>
                        }
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default App