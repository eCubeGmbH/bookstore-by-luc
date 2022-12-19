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
        [{id: ' ', name: ' ', country: ' ', birthDate: ' '}]
    )

    const  [rowEditingMode, setRowEditingMode] = useState(false)

    const [editedId, setEditedId] = useState(rowsData.id)

    const [addedRow, setAddedRow] = useState({id: '', name: '', country: '', birthDate: ''})

    const [editedRow, setEditedRow] = useState({id: '', name: '', country: '', birthDate: ''})

    const [rowAddingMode, setRowAddingMode] = useState(false)

    const addTableRows = () => {
        setRowAddingMode(true)
    }

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
    const saveInRowAddingMode = () => {
        fetch('http://localhost:9988/api/authors', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(addedRow)
            }
        )   .then(response => response.json())
            .then(json => setRowsData( [...rowsData, json]))
        console.log('hello from add')
        setRowAddingMode(false)
    }

    //put method
    const saveInRowEditingMode = (id) => {
        fetch('http://localhost:9988/api/authors/' + id, {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(editedRow)
            }
        ).then(response => {
            let updatedAuthor = response.json()
            //setRowsData([...rowsData, updatedAuthor])
            setRowEditingMode(false)
        })
    }

    const enableRowEditingMode = (rowData) => {
        setEditedId(rowData.id)
        setRowEditingMode(true)
        setEditedRow(rowData)
        console.log("enabled")
    }

    const disableRowEditingMode = () => {
        setEditedId('')
        setRowEditingMode(false)
        console.log("disabled")
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
                            <th><button  onClick={addTableRows} >Add</button></th>
                            <th><button onClick={refresh}>Refresh</button></th>
                        </tr>

                        {rowAddingMode ? //TODO convert to if Statement
                            <tr>
                                <td style={tdStyle}>{rowsData.id}</td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({...addedRow, name: e.target.value})}}/></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({...addedRow, birthDate: e.target.value})}}/></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({...addedRow, country: e.target.value})}}/></td>
                                <td>
                                    <button onClick={saveInRowAddingMode}>Save</button>
                                    <button onClick={() => {setRowAddingMode(false)}}>Cancel</button>
                                </td>
                            </tr> : <br/>
                        }

                        {rowsData.map(rowsData =>
                            <tr key={rowsData.id}>
                                        <td style={tdStyle}>{rowsData.id}</td>
                                        <td style={tdStyle}>{editedId === rowsData.id ? <input onChange={e => {setEditedRow({...editedRow, name: e.target.value})}}/> : rowsData.name}</td>
                                        <td style={tdStyle}>{editedId === rowsData.id ? <input onChange={e => {setEditedRow({...editedRow, birthDate: e.target.value})}}/> : rowsData.birthDate}</td>
                                        <td style={tdStyle}>{editedId === rowsData.id ? <input onChange={e => {setEditedRow({...editedRow, country: e.target.value})}}/> : rowsData.country}</td>
                                <td>
                                    {!rowEditingMode ?
                                        <tr>
                                            <button onClick={() => enableRowEditingMode(rowsData)}>Edit</button>
                                            <button onClick={() => deleteTableRows(rowsData.id)} >Delete</button>
                                        </tr> : <br/>
                                    }
                                </td>
                                <td>
                                    {rowEditingMode ?
                                        <tr>
                                            <button onClick={() => saveInRowEditingMode(rowsData.id)}>Save</button>
                                            <button onClick={disableRowEditingMode}>Discard changes</button>
                                        </tr> : <br/>}
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