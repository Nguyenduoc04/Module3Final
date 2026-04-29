function validate(){
    let ma = document.querySelector("[name='ma']").value;
    let regex = /^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$/;
    let dateRegex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/;

    let start = document.querySelector("[name='start']").value;
    let end = document.querySelector("[name='end']").value;

    if(!dateRegex.test(start) || !dateRegex.test(end)){
        alert("Ngày phải đúng định dạng dd/MM/yyyy");
        return false;
    }
    if(!regex.test(ma)){
        alert("Mã phải đúng dạng XXX-XX-XX");
        return false;
    }

    let dt = document.querySelector("[name='dienTich']").value;
    if(dt <= 20){
        alert("Diện tích phải > 20");
        return false;
    }

    let gia = document.querySelector("[name='gia']").value;
    if(gia < 1000000){
        alert("Giá phải > 1,000,000");
        return false;
    }

    return true;
}