import './css.css';

export default function textWritable(value, handerChange, readonly=true, className="") {
    if(readonly) {
        return <p className={`label-color ${className}`}>
                    {value}
                </p>
        ;
    } else {
        return <input type="text" class={className} onChange={handerChange} value={value} />
        ;
    }
};
