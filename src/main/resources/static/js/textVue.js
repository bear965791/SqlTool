new Vue({
    el: "#app",
    data: {
        sql:'',
        sqlformat:''
    },
    methods: {
        transfer(){
            axios.post("/transfer",{
                sql : this.sql
            })
            .then(res => {
                this.sqlformat = res.data
            })
            .catch(err => {
                console.error(err.response);
            })
        }
    }

})