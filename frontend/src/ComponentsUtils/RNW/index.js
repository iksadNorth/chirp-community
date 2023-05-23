import './css.css';

export default function textWritable(props) {
    let value = props.value
    let handlerChange = props.handlerChange
    let readonly = props.readonly ?? true
    let className = props.className ?? ""
    let no_load = props['no_load'] ?? false
    
    if(readonly) {
        return <p className={`label-color ${value || no_load ? "" : "placeholder"} ${className}`}>
                    {value}
                </p>
        ;
    } else {
        return <input type="text" className={className} onChange={handlerChange} value={value} />
        ;
    }
};
