import Linkable from '../../datastruct/Linkable';

export default class ObjStackEntity extends Linkable {
    readonly index: number;
    readonly count: number;

    constructor(index: number, count: number) {
        super();
        this.index = index;
        this.count = count;
    }
}
